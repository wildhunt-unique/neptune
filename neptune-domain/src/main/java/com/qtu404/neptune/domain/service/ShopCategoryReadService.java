package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.ShopCategory;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:41
 */
public interface ShopCategoryReadService {
    ShopCategory fetchById(Long id);

    List<ShopCategory> findByShopId(Long shopId);
}
