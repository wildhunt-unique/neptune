package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.domain.service.UserReadService;
import com.qtu404.neptune.server.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午5:05
 */
@Slf4j
@Service
public class UserReadServiceImpl implements UserReadService {
    private final UserDao userDao;

    @Autowired
    public UserReadServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Integer count(Map<String, Object> condition) {
        return this.userDao.count(condition);
    }

    @Override
    public List<User> list(Map<String,Object> condition){
        return this.userDao.list(condition);
    }

    @Override
    public User fetchById(Long userId) {
        return this.userDao.fetch(userId);
    }
}
