package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Shop;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:13
 */
public interface ShopWriteService {
    Boolean createShop(Shop toCreate);

    Boolean updateShop(Shop toUpdate);
}
