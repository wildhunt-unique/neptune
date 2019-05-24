package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.util.model.Paging;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:12
 */
public interface ShopReadService {
    Shop fetchById(Long shopId);

    List<Shop> list(Map<String, Object> toMap);

    Paging<Shop> paging(Map<String, Object> toMap);

    List<Shop> findByIds(List<Long> shopIdList);
}
