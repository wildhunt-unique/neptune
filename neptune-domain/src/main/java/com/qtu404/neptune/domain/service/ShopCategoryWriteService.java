package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.ShopCategory;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:42
 */
public interface ShopCategoryWriteService {
    Boolean create(ShopCategory toCreate);
}
