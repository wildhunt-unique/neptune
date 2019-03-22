package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.response.order.OrderLineThinResponse;
import com.qtu404.neptune.domain.model.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/22 上午10:26
 */
@Mapper(componentModel = "spring")
public interface OrderLineConverter {
    @Mappings(
            @Mapping(source = "request.id", target = "orderLineId")
    )
    OrderLineThinResponse model2ThinResponse(OrderLine request);
}
