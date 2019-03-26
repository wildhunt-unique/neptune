package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.comment.CommentCreateRequest;
import com.qtu404.neptune.api.request.comment.CommentGetRequest;
import com.qtu404.neptune.api.request.comment.CommentPagingRequest;
import com.qtu404.neptune.api.response.comment.CommentThinResponse;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:55
 */
public interface CommentFacade {
    Response<Long> createComment(CommentCreateRequest request);

    Response<CommentThinResponse> getCommentById(CommentGetRequest request);

    Response<Paging<CommentThinResponse>> paging(CommentPagingRequest request);
}
