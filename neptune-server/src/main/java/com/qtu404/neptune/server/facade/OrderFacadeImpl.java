package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.qtu404.neptune.api.facade.OrderFacade;
import com.qtu404.neptune.api.request.order.OrderCreateRequest;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.common.enums.SwitchStatusEnum;
import com.qtu404.neptune.domain.model.Order;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.domain.service.*;
import com.qtu404.neptune.server.converter.OrderConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    public OrderFacadeImpl(OrderReadService orderReadService, OrderWriteService orderWriteService, OrderConverter orderConverter, UserReadService userReadService, ShopReadService shopReadService) {
        this.orderReadService = orderReadService;
        this.orderWriteService = orderWriteService;
        this.orderConverter = orderConverter;
        this.userReadService = userReadService;
        this.shopReadService = shopReadService;
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
            Order toCreate = this.orderConverter.request2Model(request);

            User buyer = this.userReadService.fetchById(request.getBuyerId());
            AssertUtil.isExist(buyer, "buyer");

            Shop shop = this.shopReadService.fetchById(request.getShopId());
            AssertUtil.isExist(shop, "shop");

            toCreate.setBuyerName(buyer.getNickname());
            toCreate.setShopName(shop.getName());

            toCreate.setPayStatus(SwitchStatusEnum.INACTIVE.getCode());
            toCreate.setEnableStatus(SwitchStatusEnum.ACTIVE.getCode());
            toCreate.setReceiveStatus(SwitchStatusEnum.INACTIVE.getCode());
            toCreate.setReverseStatus(SwitchStatusEnum.INACTIVE.getCode());
            toCreate.setStatus(DataStatusEnum.NORMAL.getCode());
            this.orderWriteService.createOrder(toCreate);
            return toCreate.getId();
        });
    }
}
