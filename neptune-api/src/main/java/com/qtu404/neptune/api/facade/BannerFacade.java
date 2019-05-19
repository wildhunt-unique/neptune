package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.banner.*;
import com.qtu404.neptune.api.response.banner.BannerListResponse;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
public interface BannerFacade {
    Response<Boolean> enableBanner(BannerEnableRequest request);

    Response<Boolean> remove(BannerRemoveRequest request);

    Response<Boolean> updateBanner(BannerUpdateRequest request);

    Response<Long> createBanner(BannerCreateRequest request);

    Response<BannerListResponse> listBanner(BannerListRequest request);

    Response<BannerListResponse> getMobileBanner(BannerListRequest request);
}
