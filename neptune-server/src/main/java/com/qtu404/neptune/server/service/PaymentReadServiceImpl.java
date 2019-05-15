package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Payment;
import com.qtu404.neptune.domain.service.PaymentReadService;
import com.qtu404.neptune.server.dao.PaymentDao;
import com.qtu404.neptune.util.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/15
 */
@Service
public class PaymentReadServiceImpl implements PaymentReadService {
    private final PaymentDao paymentDao;

    @Autowired
    public PaymentReadServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public Paging<Payment> paging(Map<String, Object> toMap) {
        return paymentDao.paging(toMap);
    }
}
