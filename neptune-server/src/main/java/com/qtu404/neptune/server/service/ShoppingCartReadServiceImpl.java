package com.qtu404.neptune.server.service;

import com.google.common.collect.ImmutableMap;
import com.qtu404.neptune.domain.model.ShoppingCart;
import com.qtu404.neptune.domain.service.ShoppingCartReadService;
import com.qtu404.neptune.server.dao.ShoppingCartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@Component
public class ShoppingCartReadServiceImpl implements ShoppingCartReadService {
    private final ShoppingCartDao shoppingCartDao;

    @Autowired
    public ShoppingCartReadServiceImpl(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public List<ShoppingCart> list(Map<String, Object> toMap) {
        return this.shoppingCartDao.list(toMap);
    }

    @Override
    public ShoppingCart findByUserIdAndItemId(Long userId, Long itemId) {
        return this.shoppingCartDao.querySingleByCondition(ImmutableMap.of("user_id",userId,"itemId",itemId));
    }
}
