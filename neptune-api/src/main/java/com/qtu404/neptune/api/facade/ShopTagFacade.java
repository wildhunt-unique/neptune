package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.tag.TagCreateRequest;
import com.qtu404.neptune.api.request.tag.TagThinListRequest;
import com.qtu404.neptune.api.response.tag.TagThinListResponse;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:47
 */
public interface ShopTagFacade {
    Response<Long> create(TagCreateRequest request);

    Response<TagThinListResponse> thinList(TagThinListRequest request);
}
