package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Complaint;
import com.qtu404.neptune.domain.service.ComplaintReadService;
import com.qtu404.neptune.server.dao.ComplaintDao;
import com.qtu404.neptune.util.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@Component
public class ComplaintReadServiceImpl implements ComplaintReadService {
    private final ComplaintDao complaintDao;

    @Autowired
    public ComplaintReadServiceImpl(ComplaintDao complaintDao) {
        this.complaintDao = complaintDao;
    }

    @Override
    public Paging<Complaint> paging(Map<String, Object> toMap) {
        return this.complaintDao.paging(toMap);
    }
}
