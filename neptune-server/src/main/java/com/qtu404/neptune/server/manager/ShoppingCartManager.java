package com.qtu404.neptune.server.manager;

import com.google.common.collect.Lists;
import com.qtu404.neptune.domain.model.ShoppingCart;
import com.qtu404.neptune.server.dao.ShoppingCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@Component
public class ShoppingCartManager {
    private final ShoppingCartDao shoppingCartDao;

    @Autowired
    public ShoppingCartManager(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean fullUpdate(Long userId, Set<Long> shopId, List<ShoppingCart> toCreateList) {
        shoppingCartDao.removeByUserIdAndShopId(userId, Lists.newArrayList(shopId));
        if (!CollectionUtils.isEmpty(toCreateList)) {
            shoppingCartDao.save(toCreateList);
        }
        return Boolean.TRUE;
    }
}
