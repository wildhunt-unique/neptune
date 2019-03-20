package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.OrderLine;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/20 下午5:20
 */
public interface OrderLineWriteService {
    Integer batchCreate(List<OrderLine> toCreateOrderLineList);
}
