package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Item;
import com.qtu404.neptune.util.model.Paging;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:26
 */
public interface ItemReadService {
    Item fetchById(Long itemId);

    List<Item> findByCategoryId(Long id);

    Paging<Item> paging(Map<String, Object> criteria);

    List<Item> findByIds(List<Long> ids);

    List<Item> findByCategoryIds(List<Long> categoryIds);
}
