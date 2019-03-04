package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.service.ShopReadService;
import com.qtu404.neptune.server.dao.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:14
 */
// TODO: 2019/2/28 add cache
@Service
public class ShopReadServiceImpl implements ShopReadService {
    private final ShopDao shopDao;

    @Autowired
    public ShopReadServiceImpl(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    public Shop fetchById(Long shopId) {
        return shopDao.fetch(shopId);
    }
}
