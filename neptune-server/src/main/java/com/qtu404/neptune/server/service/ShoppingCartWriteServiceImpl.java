package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.ShoppingCart;
import com.qtu404.neptune.domain.service.ShoppingCartWriteService;
import com.qtu404.neptune.server.dao.ShoppingCartDao;
import com.qtu404.neptune.server.manager.ShoppingCartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@Component
public class ShoppingCartWriteServiceImpl implements ShoppingCartWriteService {
    private final ShoppingCartDao shoppingCartDao;
    private final ShoppingCartManager manager;

    @Autowired
    public ShoppingCartWriteServiceImpl(ShoppingCartDao shoppingCartDao, ShoppingCartManager manager) {
        this.shoppingCartDao = shoppingCartDao;
        this.manager = manager;
    }

    @Override
    public Boolean create(ShoppingCart toCreate) {
        return this.shoppingCartDao.save(toCreate);
    }

    @Override
    public Boolean delete(Long id) {
        return this.shoppingCartDao.remove(id);
    }

    @Override
    public Boolean update(ShoppingCart toUpdate) {
        return this.shoppingCartDao.update(toUpdate);
    }

    @Override
    public Boolean fullUpdate(Long userId, Set<Long> shopId, List<ShoppingCart> toCreateList) {
        return this.manager.fullUpdate(userId, shopId, toCreateList);
    }

    @Override
    public Boolean shopRemove(Long userId, List<Long> shopIdList) {
        return CollectionUtils.isEmpty(shopIdList) ?
                Boolean.TRUE :
                shoppingCartDao.removeByUserIdAndShopId(userId, shopIdList);
    }
}
