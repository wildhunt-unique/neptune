package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Payment;
import com.qtu404.neptune.util.model.Paging;

import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/15
 */
public interface PaymentReadService {
    Paging<Payment> paging(Map<String, Object> toMap);
}
