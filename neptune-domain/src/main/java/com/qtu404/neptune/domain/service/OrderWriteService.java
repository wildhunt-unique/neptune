package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Order;
import com.qtu404.neptune.domain.model.OrderLine;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:42
 */
public interface
OrderWriteService {
    Boolean createOrder(Order toCreate);

    Boolean update(Order toUpdateOrder);

    Boolean createOrder(Order toCreateOrder, List<OrderLine> toCreateOrderLineList);
}
