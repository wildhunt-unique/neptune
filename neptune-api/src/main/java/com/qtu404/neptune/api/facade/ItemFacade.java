package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.item.ItemAdjustRequest;
import com.qtu404.neptune.api.request.item.ItemUpdateRequest;
import com.qtu404.neptune.api.request.item.ItemCreateRequest;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:29
 */
public interface ItemFacade {
    Response<Long> createItem(ItemCreateRequest request);

    Response<Boolean> update(ItemUpdateRequest request);

    Response<Boolean> adjust(ItemAdjustRequest request);
}
