package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Comment;
import com.qtu404.neptune.util.model.Paging;

import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:53
 */
public interface CommentReadService {
    Comment findById(Long commentId);

    Paging<Comment> paging(Map<String, Object> toMap);
}
