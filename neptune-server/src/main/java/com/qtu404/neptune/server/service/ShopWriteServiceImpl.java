package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.service.ShopWriteService;
import com.qtu404.neptune.server.dao.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:13
 */
@Service
public class ShopWriteServiceImpl implements ShopWriteService {
    private final ShopDao shopDao;

    @Autowired
    public ShopWriteServiceImpl(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    public Boolean createShop(Shop toCreate) {
        return this.shopDao.save(toCreate);
    }

    @Override
    public Boolean updateShop(Shop toUpdate) {
        return this.shopDao.update(toUpdate);
    }
}
