package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.CommentFacade;
import com.qtu404.neptune.api.request.comment.CommentEnableRequest;
import com.qtu404.neptune.util.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("显示/隐藏")
    @PostMapping("enable")
    public Response<Boolean> enable(@RequestBody CommentEnableRequest request){
        return this.commentFacade.enableComment(request);
    }
}
