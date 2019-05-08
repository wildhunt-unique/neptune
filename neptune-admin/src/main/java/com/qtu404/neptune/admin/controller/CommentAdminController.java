package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.CommentFacade;
import com.qtu404.neptune.api.request.comment.CommentEnableRequest;
import com.qtu404.neptune.api.request.comment.CommentPagingRequest;
import com.qtu404.neptune.api.response.comment.CommentThinResponse;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;
import static com.qtu404.neptune.web.common.util.RequestContext.getShopId;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/15
 */
@Api(value = "评价-管理接口", tags = "评价-管理接口")
@RestController
@RequestMapping("api/admin/comment/")
public class CommentAdminController {
    @Reference
    private CommentFacade commentFacade;

    @Acl(level = AccessLevel.ADMIN)
    @ApiOperation("显示/隐藏")
    @PostMapping("enable")
    public Response<Boolean> enable(@RequestBody CommentEnableRequest request) {
        return assertResponse(this.commentFacade.enableComment(request));
    }

    @ApiOperation("当前店铺评价信息分页")
    @GetMapping("paging")
    @Acl(level = AccessLevel.SHOP)
    public Response<Paging<CommentThinResponse>> commentPaging(CommentPagingRequest request) {
        request.setShopId(getShopId());
        return assertResponse(this.commentFacade.paging(request));
    }
}
