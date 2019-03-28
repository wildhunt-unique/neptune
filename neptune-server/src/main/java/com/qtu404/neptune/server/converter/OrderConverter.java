package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.request.order.OrderUpdateRequest;
import com.qtu404.neptune.api.request.order.OrderCreateRequest;
import com.qtu404.neptune.api.response.order.OrderThinResponse;
import com.qtu404.neptune.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:40
 */
@Mapper(componentModel = "spring")
public interface OrderConverter {
    Order request2Model(OrderCreateRequest request);

    @Mappings(
            @Mapping(target = "id", source = "request.orderId")
    )
    Order request2Model(OrderUpdateRequest request);

    @Mappings(
            @Mapping(target = "orderId", source = "order.id")
    )
    OrderThinResponse model2ThinResponse(Order order);
}
