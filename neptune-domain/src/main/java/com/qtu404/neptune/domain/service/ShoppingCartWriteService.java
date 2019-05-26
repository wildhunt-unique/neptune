package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.ShoppingCart;

import java.util.List;
import java.util.Set;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
public interface ShoppingCartWriteService {
    Boolean create(ShoppingCart toCreate);

    Boolean delete(Long id);

    Boolean update(ShoppingCart toUpdate);

    Boolean fullUpdate(Long userId, Set<Long> shopId, List<ShoppingCart> toCreateList);
}
