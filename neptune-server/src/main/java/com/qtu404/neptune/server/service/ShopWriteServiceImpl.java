package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.model.TagBinding;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.domain.service.ShopWriteService;
import com.qtu404.neptune.server.dao.ShopDao;
import com.qtu404.neptune.server.manager.ShopManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:13
 */
@Service
public class ShopWriteServiceImpl implements ShopWriteService {
    private final ShopDao shopDao;

    private final ShopManager shopManager;

    @Autowired
    public ShopWriteServiceImpl(ShopDao shopDao, ShopManager shopManager) {
        this.shopDao = shopDao;
        this.shopManager = shopManager;
    }

    @Override
    public Boolean createShop(Shop toCreate) {
        return this.shopDao.save(toCreate);
    }

    @Override
    public Boolean updateShop(Shop toUpdate) {
        return this.shopDao.update(toUpdate);
    }

    @Override
    public void createShop(User seller, Shop toCreate, List<TagBinding> toCreateTagBinding) {
        this.shopManager.createShop(seller, toCreate, toCreateTagBinding);
    }
}
