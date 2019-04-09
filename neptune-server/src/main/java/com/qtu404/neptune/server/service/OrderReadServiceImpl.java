package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Order;
import com.qtu404.neptune.domain.service.OrderReadService;
import com.qtu404.neptune.server.dao.OrderDao;
import com.qtu404.neptune.util.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:41
 */
@Service
public class OrderReadServiceImpl implements OrderReadService {
    private final OrderDao orderDao;

    @Autowired
    public OrderReadServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order findById(Long orderId) {
        return this.orderDao.fetch(orderId);
    }

    @Override
    public Paging<Order> paging(Map<String, Object> condition) {
        return this.orderDao.paging(condition);
    }
}
