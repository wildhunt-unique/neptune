package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qtu404.neptune.api.facade.OrderFacade;
import com.qtu404.neptune.api.request.order.*;
import com.qtu404.neptune.api.response.order.OrderDetailResponse;
import com.qtu404.neptune.api.response.order.OrderLineThinResponse;
import com.qtu404.neptune.api.response.order.OrderThinResponse;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.common.constant.ExtraKey;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.common.enums.SwitchStatusEnum;
import com.qtu404.neptune.domain.model.*;
import com.qtu404.neptune.domain.service.*;
import com.qtu404.neptune.server.converter.OrderConverter;
import com.qtu404.neptune.server.converter.OrderLineConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:43
 */
@Component
@Slf4j
@Service(interfaceClass = OrderFacade.class)
public class OrderFacadeImpl implements OrderFacade {
    private final OrderReadService orderReadService;
    private final OrderWriteService orderWriteService;
    private final UserReadService userReadService;
    private final ShopReadService shopReadService;
    private final OrderConverter orderConverter;
    private final ItemReadService itemReadService;
    private final OrderLineReadService orderLineReadService;
    private final OrderLineConverter orderLineConverter;

    @Autowired
    public OrderFacadeImpl(OrderReadService orderReadService, OrderWriteService orderWriteService, OrderConverter orderConverter, UserReadService userReadService, ShopReadService shopReadService, ItemReadService itemReadService, OrderLineReadService orderLineReadService, OrderLineConverter orderLineConverter) {
        this.orderReadService = orderReadService;
        this.orderWriteService = orderWriteService;
        this.orderConverter = orderConverter;
        this.userReadService = userReadService;
        this.shopReadService = shopReadService;
        this.itemReadService = itemReadService;
        this.orderLineReadService = orderLineReadService;
        this.orderLineConverter = orderLineConverter;
    }

    /**
     * 订单创建
     *
     * @param request 请求参数
     * @return 订单id
     */
    @Override
    public Response<Long> createOrder(OrderCreateRequest request) {
        return execute(request, param -> {
            Order toCreateOrder = this.orderConverter.request2Model(request);

            // TODO: 2019/3/20 check buyer and shop status

            User buyer = this.userReadService.fetchById(request.getBuyerId());
            AssertUtil.isExist(buyer, "buyer");

            Shop shop = this.shopReadService.fetchById(request.getShopId());
            AssertUtil.isExist(shop, "shop");

            // 存在的商品，构造为id -> item
            Map<Long, Item> existIdToItem =
                    this.itemReadService.findByIds(request.getOrderLine().stream().map(ItemOrderLineCreateRequest::getItemId).collect(Collectors.toList()))
                            .stream()
                            .collect(Collectors.toMap(Item::getId, item -> item));
            // 订单支付信息
            int[] itemTotalAmount = new int[]{0};
            int[] paidTotalAmount = new int[]{0};

            // 构造商品级订单行
            List<OrderLine> toCreateOrderLineList = Lists.newArrayList();
            request.getOrderLine().forEach(item -> {
                // 商品信息检查
                Item existItem = existIdToItem.get(item.getItemId());
                this.itemOrderLineCheck(existItem, item, shop);
                // 订单行model构建
                OrderLine toCreateOrderLine = new OrderLine();
                toCreateOrderLine.setOutId(request.getOutId());
                // 订单行状态
                toCreateOrderLine.setStatus(DataStatusEnum.NORMAL.getCode());
                toCreateOrderLine.setReceiveStatus(SwitchStatusEnum.INIT.getCode());
                // 买家信息
                toCreateOrderLine.setBuyerId(buyer.getId());
                toCreateOrderLine.setBuyerName(buyer.getNickname());
                // 卖家信息
                toCreateOrderLine.setShopId(shop.getId());
                toCreateOrderLine.setShopName(shop.getName());
                // 商品信息
                toCreateOrderLine.setItemId(existItem.getId());
                toCreateOrderLine.setItemAttribute(item.getItemAttribute());
                toCreateOrderLine.setItemName(existItem.getName());
                toCreateOrderLine.setItemImage(existItem.getMainImage());
                // 支付信息
                toCreateOrderLine.setPaidAmount(item.getPaidAmount());
                toCreateOrderLine.setQuantity(item.getQuantity());
                // 计算订单级支付信息
                itemTotalAmount[0] += item.getQuantity();
                paidTotalAmount[0] += item.getPaidAmount();
                toCreateOrderLineList.add(toCreateOrderLine);
            });

            // 订单初始化
            toCreateOrder.setBuyerName(buyer.getNickname());
            toCreateOrder.setShopName(shop.getName());
            // 订单支付信息
            toCreateOrder.setPaidAmount(Math.max(0, paidTotalAmount[0]));
            toCreateOrder.setItemTotalAmount(Math.max(0, itemTotalAmount[0]));
            // 设置各种状态
            toCreateOrder.setPayStatus(SwitchStatusEnum.INACTIVE.getCode());
            toCreateOrder.setEnableStatus(SwitchStatusEnum.INIT.getCode());
            toCreateOrder.setReceiveStatus(SwitchStatusEnum.INIT.getCode());
            toCreateOrder.setReverseStatus(SwitchStatusEnum.INIT.getCode());
            toCreateOrder.setStatus(DataStatusEnum.NORMAL.getCode());
            // 将用户电话号码存入extra
            Map<String, Object> extra = Maps.newHashMap();
            extra.put(ExtraKey.ORDER_BUYER_MOBILE, buyer.getMobile());
            toCreateOrder.setExtra(extra);

            this.orderWriteService.createOrder(toCreateOrder, toCreateOrderLineList);
            return toCreateOrder.getId();
        });
    }

    /**
     * 订单更新
     *
     * @param request 更新参数
     * @return 是否操作成功
     */
    @Override
    public Response<Boolean> updateOrder(OrderUpdateRequest request) {
        return execute(request, param -> {
            Order existOrder = this.orderReadService.findById(request.getOrderId());
            AssertUtil.isExist(existOrder, "order");

            Order toUpdateOrder = this.orderConverter.request2Model(request);
            if (ObjectUtils.nullSafeEquals(toUpdateOrder.getPayStatus(), SwitchStatusEnum.ACTIVE)) {
                if (ObjectUtils.nullSafeEquals(existOrder.getPayStatus(), SwitchStatusEnum.ACTIVE)) {
                    throw new ServiceException("payment.has.been.completed");
                }
                toUpdateOrder.setPayAt(new Date());
            }
            // TODO: 2019/3/21 to check status
            return this.orderWriteService.update(toUpdateOrder);
        });
    }

    /**
     * 订单详情
     *
     * @param request 请求参数
     * @return 详情信息
     */
    @Override
    public Response<OrderDetailResponse> getOrderDetail(OrderDetailRequest request) {
        return execute(request, param -> {
            Order order = this.orderReadService.findById(request.getOrderId());
            AssertUtil.isExist(order, "order");
            List<OrderLine> orderLineList = this.orderLineReadService.findByOrderId(order.getId());

            OrderThinResponse orderThinResponse = this.orderConverter.model2ThinResponse(order);
            List<OrderLineThinResponse> orderLineThinResponseList = orderLineList.stream()
                    .map(this.orderLineConverter::model2ThinResponse)
                    .collect(Collectors.toList());

            return OrderDetailResponse.builder()
                    .orderLineThinResponseList(orderLineThinResponseList)
                    .orderThinResponse(orderThinResponse)
                    .build();
        });
    }

    /**
     * 订单分页
     *
     * @param request 分页参数
     * @return 分页信息
     */
    @Override
    public Response<Paging<OrderThinResponse>> paging(OrderPagingRequest request) {
        return execute(request, param -> {
            Map<String, Object> condition = request.toMap();
            if (Objects.nonNull(request.getOrderId())) {
                condition.put("id", request.getOrderId());
            }
            Paging<Order> orderPaging = this.orderReadService.paging(condition);
            return new Paging<>(
                    orderPaging.getTotal(),
                    orderPaging.getData().stream()
                            .map(e -> {
                                OrderThinResponse response = this.orderConverter.model2ThinResponse(e);
                                if (Objects.nonNull(e.getExtra())) {
                                    Object mobile = e.getExtra().get(ExtraKey.ORDER_BUYER_MOBILE);
                                    response.setBuyerMobile(Objects.nonNull(mobile) ? mobile.toString() : ConstantValues.BLANK);
                                }
                                return response;
                            })
                            .collect(Collectors.toList())
            );
        });
    }

    private void itemOrderLineCheck(Item existItem, ItemOrderLineCreateRequest item, Shop shop) {
        if (Objects.isNull(existItem) || existItem.getStatus().equals(DataStatusEnum.DELETE.getCode())) {
            throw new ServiceException("item.not.exist");
        }
        if (!ObjectUtils.nullSafeEquals(existItem.getShopId(), shop.getId())) {
            throw new ServiceException("item.not.belong.to.shop");
        }
        if (existItem.getStatus().equals(DataStatusEnum.FREEZE.getCode())) {
            throw new ServiceException("item.not.put.away");
        }
        if (item.getQuantity() > existItem.getInventory()) {
            throw new ServiceException("item.low.stocks");
        }
    }
}
