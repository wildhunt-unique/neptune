package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Order;
import com.qtu404.neptune.domain.model.OrderLine;
import com.qtu404.neptune.domain.service.OrderWriteService;
import com.qtu404.neptune.server.dao.OrderDao;
import com.qtu404.neptune.server.manager.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:42
 */
@Service
public class OrderWriteServiceImpl implements OrderWriteService {
    private final OrderDao orderDao;

    private final OrderManager orderManager;

    @Autowired
    public OrderWriteServiceImpl(OrderDao orderDao, OrderManager orderManager) {
        this.orderDao = orderDao;
        this.orderManager = orderManager;
    }

    @Override
    public Boolean createOrder(Order toCreate) {
        return this.orderDao.save(toCreate);
    }

    @Override
    public Boolean update(Order toUpdateOrder) {
        return orderDao.update(toUpdateOrder);
    }

    @Override
    public Boolean createOrder(Order toCreateOrder, List<OrderLine> toCreateOrderLineList) {
        return orderManager.createOrder(toCreateOrder, toCreateOrderLineList);
    }
}
