package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Item;
import com.qtu404.neptune.domain.service.ItemReadService;
import com.qtu404.neptune.server.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
