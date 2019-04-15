package com.qtu404.neptune.web.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.CommentFacade;
import com.qtu404.neptune.api.request.comment.CommentCreateRequest;
import com.qtu404.neptune.api.request.comment.CommentGetRequest;
import com.qtu404.neptune.api.request.comment.CommentPagingRequest;
import com.qtu404.neptune.api.response.comment.CommentThinResponse;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    public Response<Long> createComment(CommentCreateRequest request, HttpSession session) {
        request.setUserId(UserUtils.getId(session));
        return this.commentFacade.createComment(request);
    }

    @ApiOperation("查看评价信息")
    @PostMapping("get")
    public Response<CommentThinResponse> getCommentById(CommentGetRequest request) {
        return this.commentFacade.getCommentById(request);
    }

    @ApiOperation("评价信息分页")
    @PostMapping("paging")
    public Response<Paging<CommentThinResponse>> commentPaging(CommentPagingRequest request) {
        return this.commentFacade.paging(request);
    }
}
