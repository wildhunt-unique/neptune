package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.request.order.OrderCreateRequest;
import com.qtu404.neptune.domain.model.Order;
import org.mapstruct.Mapper;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:40
 */
@Mapper(componentModel = "spring")
public interface OrderConverter {
    Order request2Model(OrderCreateRequest request);
}
