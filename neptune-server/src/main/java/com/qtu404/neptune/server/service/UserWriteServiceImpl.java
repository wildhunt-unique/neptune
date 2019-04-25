package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.domain.service.UserWriteService;
import com.qtu404.neptune.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/27 下午6:57
 */
@Service
public class UserWriteServiceImpl implements UserWriteService {
    private final UserDao userDao;

    @Autowired
    public UserWriteServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Boolean update(User user){
        return userDao.update(user);
    }

    @Override
    public Boolean createUser(User user) {
        return this.userDao.save(user);
    }
}
