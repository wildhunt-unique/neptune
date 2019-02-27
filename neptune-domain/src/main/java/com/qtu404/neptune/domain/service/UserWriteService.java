package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.User;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/27 下午6:56
 */
public interface UserWriteService {
    Boolean update(User user);

    Boolean addUser(User user);
}
