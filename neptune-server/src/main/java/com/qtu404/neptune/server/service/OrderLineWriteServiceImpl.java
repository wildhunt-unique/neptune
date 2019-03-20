package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.OrderLine;
import com.qtu404.neptune.domain.service.OrderLineWriteService;
import com.qtu404.neptune.server.dao.OrderLineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/20 下午5:20
 */
@Service
public class OrderLineWriteServiceImpl implements OrderLineWriteService {
    private final OrderLineDao orderLineDao;

    @Autowired
    public OrderLineWriteServiceImpl(OrderLineDao orderLineDao) {
        this.orderLineDao = orderLineDao;
    }

    @Override
    public Integer batchCreate(List<OrderLine> toCreateOrderLineList) {
        return this.orderLineDao.save(toCreateOrderLineList);
    }
}
