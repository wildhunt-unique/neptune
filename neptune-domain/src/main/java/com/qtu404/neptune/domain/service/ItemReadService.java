package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Item;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:26
 */
public interface ItemReadService {
    Item fetchById(Long itemId);

    List<Item> findByCategoryId(Long id);
}
