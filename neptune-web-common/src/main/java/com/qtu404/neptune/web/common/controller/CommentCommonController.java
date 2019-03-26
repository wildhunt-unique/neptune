package com.qtu404.neptune.web.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.CommentFacade;
import com.qtu404.neptune.api.request.comment.CommentCreateRequest;
import com.qtu404.neptune.util.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:57
 */
@Api(value = "评价-通用接口", tags = "评价-通用接口")
@RestController
@RequestMapping("api/common/comment/")
public class CommentCommonController {
    @Reference
    private CommentFacade commentFacade;

    @ApiOperation("创建评价")
    @PostMapping("create")
    public Response<Long> createComment(CommentCreateRequest request) {
        return this.commentFacade.createComment(request);
    }
}
