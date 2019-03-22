package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Order;
import com.qtu404.neptune.domain.service.OrderWriteService;
import com.qtu404.neptune.server.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:42
 */
@Service
public class OrderWriteServiceImpl implements OrderWriteService {
    private final OrderDao orderDao;

    @Autowired
    public OrderWriteServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Boolean createOrder(Order toCreate) {
        return this.orderDao.save(toCreate);
    }

    @Override
    public Boolean update(Order toUpdateOrder) {
        return orderDao.update(toUpdateOrder);
    }
}
