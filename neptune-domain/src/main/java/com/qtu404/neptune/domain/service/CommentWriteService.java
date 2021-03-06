package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Comment;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:53
 */
public interface CommentWriteService {
    Boolean createComment(Comment toCreateComment);

    Boolean update(Comment comment);
}
