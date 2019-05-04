package com.qtu404.neptune.server.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qtu404.neptune.domain.model.Item;
import com.qtu404.neptune.domain.service.ItemReadService;
import com.qtu404.neptune.server.dao.ItemDao;
import com.qtu404.neptune.util.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:26
 */
@Service
public class ItemReadServiceImpl implements ItemReadService {
    private final ItemDao itemDao;

    @Autowired
    public ItemReadServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public Item fetchById(Long itemId) {
        return this.itemDao.fetch(itemId);
    }

    @Override
    public List<Item> findByCategoryId(Long categoryId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("categoryId", categoryId);
        return this.itemDao.list(param);
    }

    @Override
    public Paging<Item> paging(Map<String, Object> criteria) {
        return this.itemDao.paging(criteria);
    }

    @Override
    public List<Item> findByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Lists.newArrayList();
        } else {
            return this.itemDao.fetch(ids);
        }
    }

    @Override
    public List<Item> findByCategoryIds(List<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return Lists.newArrayList();
        } else {
            return this.itemDao.findByCategoryIds(categoryIds);
        }
    }
}
