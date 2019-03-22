package com.qtu404.neptune.server.service;

import com.google.common.collect.Maps;
import com.qtu404.neptune.domain.model.OrderLine;
import com.qtu404.neptune.domain.service.OrderLineReadService;
import com.qtu404.neptune.server.dao.OrderLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/20 下午5:19
 */
@Service
public class OrderLineReadServiceImpl implements OrderLineReadService {
    private final OrderLineDao orderLineDao;

    @Autowired
    public OrderLineReadServiceImpl(OrderLineDao orderLineDao) {
        this.orderLineDao = orderLineDao;
    }

    @Override
    public List<OrderLine> findByOrderId(Long orderId) {
        Map<String, Object> criteria = Maps.newHashMap();
        criteria.put("orderId", orderId);
        return orderLineDao.list(criteria);
    }
}
