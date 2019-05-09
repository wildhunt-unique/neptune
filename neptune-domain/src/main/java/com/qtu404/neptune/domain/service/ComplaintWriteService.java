package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Complaint;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
public interface ComplaintWriteService {
    Boolean create(Complaint toCreateComplaint);
}
