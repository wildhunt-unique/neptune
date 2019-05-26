package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.qtu404.neptune.api.facade.ShoppingCartFacade;
import com.qtu404.neptune.api.request.order.ShoppingCartCreateOrUpdateRequest;
import com.qtu404.neptune.api.request.order.ShoppingCartDetailRequest;
import com.qtu404.neptune.api.request.order.ShoppingCartShopRemoveAllRequest;
import com.qtu404.neptune.api.request.order.ShoppingFullUpdateRequest;
import com.qtu404.neptune.api.response.order.ShoppingCartDetailResponse;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.model.Item;
import com.qtu404.neptune.domain.model.ShoppingCart;
import com.qtu404.neptune.domain.service.ItemReadService;
import com.qtu404.neptune.domain.service.ShopReadService;
import com.qtu404.neptune.domain.service.ShoppingCartReadService;
import com.qtu404.neptune.domain.service.ShoppingCartWriteService;
import com.qtu404.neptune.server.converter.ShopConverter;
import com.qtu404.neptune.server.converter.ShoppingCartConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@Component
@Service(interfaceClass = ShoppingCartFacade.class)
public class ShoppingCartFacadeImpl implements ShoppingCartFacade {
    private final ShoppingCartReadService shoppingCartReadService;
    private final ShoppingCartWriteService shoppingCarWriteService;
    private final ShopConverter shopConverter;
    private final ShopReadService shopReadService;
    private final ShoppingCartConverter shoppingCartConverter;
    private final ItemReadService itemReadService;

    @Autowired
    public ShoppingCartFacadeImpl(ShoppingCartReadService shoppingCartReadService, ShoppingCartWriteService shoppingCarWriteService, ShopReadService shopReadService, ShopConverter shopConverter, ShoppingCartConverter shoppingCartConverter, ItemReadService itemReadService) {
        this.shoppingCartReadService = shoppingCartReadService;
        this.shoppingCarWriteService = shoppingCarWriteService;
        this.shopReadService = shopReadService;
        this.shopConverter = shopConverter;
        this.shoppingCartConverter = shoppingCartConverter;
        this.itemReadService = itemReadService;
    }

    @Override
    public Response<List<ShoppingCartDetailResponse>> getShoppingCartDetail(ShoppingCartDetailRequest request) {
        return execute(request, param -> {
            List<ShoppingCart> shoppingCartList = this.shoppingCartReadService.list(request.toMap());
            Multimap<Long, ShoppingCart> shopIdToShoppingCart = ArrayListMultimap.create();
            shoppingCartList.forEach(shoppingCart -> shopIdToShoppingCart.put(shoppingCart.getShopId(), shoppingCart));
            return this.shopReadService.findByIds(
                    Lists.newArrayList(shoppingCartList.stream().map(ShoppingCart::getShopId).collect(Collectors.toSet()))
            ).stream()
                    .map(shop -> ShoppingCartDetailResponse.builder()
                            .shopThinResponse(this.shopConverter.model2ThinResponse(shop))
                            .shoppingCartLine(shopIdToShoppingCart.get(shop.getId())
                                    .stream()
                                    .map(shoppingCartConverter::model2ThinResponse)
                                    .collect(Collectors.toList()))
                            .build())
                    .collect(Collectors.toList());
        });
    }

    @Override
    public Response<Boolean> fullUpdate(ShoppingFullUpdateRequest request) {
        return execute(request, param -> {
            List<Long> itemIdList = request.getShoppingCartList().stream()
                    .map(ShoppingCartCreateOrUpdateRequest::getItemId)
                    .collect(Collectors.toList());
            Map<Long, Item> idToItem = this.itemReadService.findByIds(itemIdList).stream()
                    .collect(Collectors.toMap(Item::getId, item -> item));

            Set<Long> shopIdSet = idToItem.values().stream().map(Item::getShopId).collect(Collectors.toSet());
            shopIdSet.add(request.getShopId());

            return this.shoppingCarWriteService.fullUpdate(request.getUserId(), shopIdSet, request.getShoppingCartList().stream()
                    .map(this::getCreateModel)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList())
            );
        });
    }

    @Override
    public Response<Boolean> shopRemove(ShoppingCartShopRemoveAllRequest request) {
        return execute(request, param -> {
            return this.shoppingCarWriteService.shopRemove(request.getUserId(),request.getShopIdList());
        });
    }

    @Override
    public Response<Boolean> createOrUpdate(ShoppingCartCreateOrUpdateRequest request) {
        return execute(request, param -> {
            ShoppingCart shoppingCart = this.shoppingCartReadService.findByUserIdAndItemId(request.getUserId(), request.getItemId());
            return Objects.isNull(shoppingCart) ?
                    this.create(request) :
                    this.updateOrDelete(shoppingCart, request);
        });
    }

    private Boolean create(ShoppingCartCreateOrUpdateRequest request) {
        ShoppingCart toCreate = this.getCreateModel(request);
        return Objects.isNull(toCreate) ?
                Boolean.TRUE :
                this.shoppingCarWriteService.create(toCreate);
    }

    private Boolean updateOrDelete(ShoppingCart exist, ShoppingCartCreateOrUpdateRequest request) {
        if (request.getQuantity() <= 0) {
            return this.shoppingCarWriteService.delete(exist.getId());
        } else {
            ShoppingCart toUpdate = this.shoppingCartConverter.request2Model(request);
            toUpdate.setId(exist.getId());
            return this.shoppingCarWriteService.update(toUpdate);
        }
    }

    private ShoppingCart getCreateModel(ShoppingCartCreateOrUpdateRequest request) {
        if (request.getQuantity() <= 0) {
            return null;
        }
        Item item = this.checkItem(request.getItemId());
        ShoppingCart toCreate = this.shoppingCartConverter.request2Model(request);
        toCreate.setShopId(item.getShopId());
        toCreate.setItemName(item.getName());
        toCreate.setItemImage(item.getMainImage());
        toCreate.setPrice(item.getPrice());
        toCreate.setStatus(DataStatusEnum.NORMAL.getCode());
        return toCreate;
    }

    private Item checkItem(Long itemId) {
        Item item = this.itemReadService.fetchById(itemId);
        AssertUtil.isExist(item, "item");
        if (item.getStatus().equals(DataStatusEnum.FREEZE.getCode())
                || item.getStatus().equals(DataStatusEnum.DELETE.getCode())) {
            throw new ServiceException("item.lose.efficacy");
        }
        return item;
    }
}
