package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.BannerFacade;
import com.qtu404.neptune.api.request.banner.*;
import com.qtu404.neptune.api.request.comment.CommentEnableRequest;
import com.qtu404.neptune.api.response.banner.BannerListResponse;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@Api(value = "广告-管理接口", tags = "广告-管理接口")
@RestController
@RequestMapping("api/admin/banner/")
public class BannerAdminController {
    @Reference
    private BannerFacade bannerFacade;

    @Acl(level = AccessLevel.ADMIN)
    @ApiOperation("广告图 显示/禁用")
    @PostMapping("enable")
    public Response<Boolean> enable(@RequestBody BannerEnableRequest request) {
        return assertResponse(this.bannerFacade.enableBanner(request));
    }

    @Acl(level = AccessLevel.ADMIN)
    @ApiOperation("广告图 删除")
    @PostMapping("remove")
    public Response<Boolean> enable(@RequestBody BannerRemoveRequest request) {
        return assertResponse(this.bannerFacade.remove(request));
    }

    @Acl(level = AccessLevel.ADMIN)
    @ApiOperation("广告图 创建")
    @PostMapping("create")
    public Response<Long> enable(@RequestBody BannerCreateRequest request) {
        return assertResponse(this.bannerFacade.createBanner(request));
    }

    @Acl(level = AccessLevel.ADMIN)
    @ApiOperation("广告图 更新")
    @PostMapping("update")
    public Response<Boolean> enable(@RequestBody BannerUpdateRequest request) {
        return assertResponse(this.bannerFacade.updateBanner(request));
    }

    @Acl(level = AccessLevel.ADMIN)
    @ApiOperation("广告图 list")
    @GetMapping("list")
    public Response<BannerListResponse> enable(BannerListRequest request) {
        return assertResponse(this.bannerFacade.listBanner(request));
    }
}
