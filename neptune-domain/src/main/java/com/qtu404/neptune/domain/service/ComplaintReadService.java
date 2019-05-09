package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Complaint;
import com.qtu404.neptune.util.model.Paging;

import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
public interface ComplaintReadService {
    Paging<Complaint> paging(Map<String, Object> toMap);
}
