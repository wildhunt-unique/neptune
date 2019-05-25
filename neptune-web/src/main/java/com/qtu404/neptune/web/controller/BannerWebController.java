package com.qtu404.neptune.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.BannerFacade;
import com.qtu404.neptune.api.request.banner.BannerListRequest;
import com.qtu404.neptune.api.response.banner.BannerListResponse;
import com.qtu404.neptune.util.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@Api(value = "banner-web接口", tags = "banner-web接口")
@RestController
@RequestMapping("api/web/banner/")
public class BannerWebController {
    @Reference
    private BannerFacade bannerFacade;

    @ApiOperation("获得店铺详情")
    @GetMapping("get/mobile")
    public Response<BannerListResponse> getMobile(BannerListRequest request) {
        return assertResponse(this.bannerFacade.getMobileBanner(request));
    }
}
