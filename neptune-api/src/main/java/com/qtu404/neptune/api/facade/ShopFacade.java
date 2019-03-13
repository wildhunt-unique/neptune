package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.shop.ShopDetailRequest;
import com.qtu404.neptune.api.request.shop.ShopPagingRequest;
import com.qtu404.neptune.api.response.shop.ShopDetailResponse;
import com.qtu404.neptune.api.response.shop.ShopThinResponse;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.api.request.shop.ShopCreateRequest;
import com.qtu404.neptune.api.request.shop.ShopUpdateRequest;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:10
 */
public interface ShopFacade {
    Response<Long> createShop(ShopCreateRequest request);

    Response<Boolean> updateShopInfo(ShopUpdateRequest request);

    Response<ShopDetailResponse> getShopDetail(ShopDetailRequest request);

    Response<Paging<ShopThinResponse>> shopPaging(ShopPagingRequest request);
}
