package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.service.CommentWriteService;
import com.qtu404.neptune.server.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:53
 */
@Service
public class CommentWriteServiceImpl implements CommentWriteService {
    private final CommentDao commentDao;

    @Autowired
    public CommentWriteServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
