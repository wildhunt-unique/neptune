package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Shop;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:12
 */
public interface ShopReadService {
    Shop fetchById(Long shopId);
}
