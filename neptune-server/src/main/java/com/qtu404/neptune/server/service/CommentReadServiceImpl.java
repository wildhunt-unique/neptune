package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Comment;
import com.qtu404.neptune.domain.service.CommentReadService;
import com.qtu404.neptune.server.dao.CommentDao;
import com.qtu404.neptune.util.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:52
 */
@Service
public class CommentReadServiceImpl implements CommentReadService {
    private final CommentDao commentDao;

    @Autowired
    public CommentReadServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public Comment findById(Long commentId) {
        return this.commentDao.fetch(commentId);
    }

    @Override
    public Paging<Comment> paging(Map<String, Object> toMap) {
        return this.commentDao.paging(toMap);
    }
}
