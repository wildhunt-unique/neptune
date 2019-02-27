package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.User;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午5:03
 */
public interface UserReadService {
    Integer count(Map<String, Object> condition);

    List<User> list(Map<String, Object> condition);

    User fetchById(Long userId);
}
