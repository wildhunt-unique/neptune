package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
public interface ShoppingCartReadService {
    List<ShoppingCart> list(Map<String, Object> toMap);

    @Deprecated
    ShoppingCart findByUserIdAndItemId(Long userId, Long itemId);

    ShoppingCart getById(Long shoppingCardId);
}
