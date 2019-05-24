package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.shop.*;
import com.qtu404.neptune.api.response.shop.ShopCategoryListResponse;
import com.qtu404.neptune.api.response.shop.ShopDetailResponse;
import com.qtu404.neptune.api.response.shop.ShopThinResponse;
import com.qtu404.neptune.api.response.shop.ShopWithSearchItemResponse;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:10
 */
public interface ShopFacade {
    Response<Long> createShop(ShopCreateRequest request);

    Response<Boolean> updateShopInfo(ShopUpdateRequest request);

    Response<ShopDetailResponse> getShopDetail(ShopDetailRequest request);

    Response<Paging<ShopThinResponse>> shopPaging(ShopPagingRequest request);

    Response<ShopCategoryListResponse> queryCategoryList(ShopCategoryQueryRequest request);

    Response<ShopThinResponse> getShopById(ShopGetRequest request);

    Response<List<ShopWithSearchItemResponse>> getShopWithSearchItem(ShopWithSearchItemRequest request);
}
