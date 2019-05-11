package com.qtu404.neptune.server.manager;

import com.qtu404.neptune.domain.model.Order;
import com.qtu404.neptune.domain.model.OrderLine;
import com.qtu404.neptune.server.dao.OrderDao;
import com.qtu404.neptune.server.dao.OrderLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 下午2:13
 */
@Component
public class OrderManager {
    private final OrderDao orderDao;
    private final OrderLineDao orderLineDao;

    @Autowired
    public OrderManager(OrderDao orderDao, OrderLineDao orderLineDao) {
        this.orderDao = orderDao;
        this.orderLineDao = orderLineDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean createOrder(Order toCreateOrder, List<OrderLine> toCreateOrderLineList) {
        this.orderDao.save(toCreateOrder);
        toCreateOrderLineList.forEach(orderLine -> orderLine.setOrderId(toCreateOrder.getId()));
        this.orderLineDao.save(toCreateOrderLineList);
        return Boolean.TRUE;
    }
}
