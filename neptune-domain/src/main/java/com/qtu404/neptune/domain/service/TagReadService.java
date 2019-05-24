package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Tag;

import java.util.Arrays;
import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:45
 */
public interface TagReadService {
    List<Tag> list();

    List<Tag> findByType(Integer type);

    List<Tag> findByIds(List<Long> tagIds);

    List<Tag> findByShopId(Long id);
}
