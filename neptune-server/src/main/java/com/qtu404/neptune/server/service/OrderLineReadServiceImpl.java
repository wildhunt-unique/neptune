package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.service.OrderLineReadService;
import com.qtu404.neptune.server.dao.OrderLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
