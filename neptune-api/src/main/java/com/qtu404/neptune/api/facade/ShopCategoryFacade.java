package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.shop.ShopCategoryCreateRequest;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:43
 */
public interface ShopCategoryFacade {
    Response<Long> create(ShopCategoryCreateRequest request);
}
