package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.service.PaymentWriteService;
import com.qtu404.neptune.server.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/15
 */
@Service
public class PaymentWriteServiceImpl implements PaymentWriteService {
    private final PaymentDao paymentDao;

    @Autowired
    public PaymentWriteServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
}
