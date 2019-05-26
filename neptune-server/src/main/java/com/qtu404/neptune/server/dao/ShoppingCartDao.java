package com.qtu404.neptune.server.dao;

import com.google.common.collect.ImmutableMap;
import com.qtu404.neptune.domain.model.ShoppingCart;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@Repository
public class ShoppingCartDao extends MyBatisDAO<ShoppingCart> {
    public Boolean removeByUserIdAndShopId(Long userId, List<Long> shopIdList) {
        if (CollectionUtils.isEmpty(shopIdList)) {
            return Boolean.TRUE;
        }
        Map<String, Object> condition = ImmutableMap.of(
                "userId", userId,
                "shopIdList", shopIdList
        );
        return this.sqlSession.delete(this.sqlId("shopRemove"), condition) > 0;
    }
}
