package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Order;
import com.qtu404.neptune.util.model.Paging;

import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:41
 */
public interface OrderReadService {
    Order findById(Long orderId);

    Paging<Order> paging(Map<String, Object> condition);
}
