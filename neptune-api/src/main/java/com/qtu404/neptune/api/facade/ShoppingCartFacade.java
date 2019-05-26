package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.order.ShoppingCartCreateOrUpdateRequest;
import com.qtu404.neptune.api.request.order.ShoppingCartDetailRequest;
import com.qtu404.neptune.api.request.order.ShoppingCartShopRemoveAllRequest;
import com.qtu404.neptune.api.request.order.ShoppingFullUpdateRequest;
import com.qtu404.neptune.api.response.order.ShoppingCartDetailResponse;
import com.qtu404.neptune.util.model.Response;

import java.util.List;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
public interface ShoppingCartFacade {
    Response<List<ShoppingCartDetailResponse>> getShoppingCartDetail(ShoppingCartDetailRequest request);

    Response<Boolean> createOrUpdate(ShoppingCartCreateOrUpdateRequest request);

    Response<Boolean> fullUpdate(ShoppingFullUpdateRequest request);

    Response<Boolean> shopRemove(ShoppingCartShopRemoveAllRequest request);
}
