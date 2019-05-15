package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.response.order.PaymentThinResponse;
import com.qtu404.neptune.domain.model.Payment;
import org.mapstruct.Mapper;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/15
 */
@Mapper(componentModel = "spring")

public interface PaymentConverter {
    PaymentThinResponse model2ThinResponse(Payment payment);
}
