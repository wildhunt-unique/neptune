package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Item;
import com.qtu404.neptune.domain.service.ItemWriteService;
import com.qtu404.neptune.server.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:27
 */
@Service
public class ItemWriteServiceImpl implements ItemWriteService {
    private final ItemDao itemDao;

    @Autowired
    public ItemWriteServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public Boolean createItem(Item toCreateItem) {
        return itemDao.save(toCreateItem);
    }

    @Override
    public Boolean update(Item toUpdateItem) {
        return itemDao.update(toUpdateItem);
    }
}
