package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.response.order.PaymentThinResponse;
import com.qtu404.neptune.domain.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/15
 */
@Mapper(componentModel = "spring")

public interface PaymentConverter {
    @Mappings(
            @Mapping(source = "payment.id", target = "paymentId")
    )
    PaymentThinResponse model2ThinResponse(Payment payment);
}
