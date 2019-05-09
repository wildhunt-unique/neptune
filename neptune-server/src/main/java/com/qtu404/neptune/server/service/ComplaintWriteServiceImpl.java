package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Complaint;
import com.qtu404.neptune.domain.service.ComplaintWriteService;
import com.qtu404.neptune.server.dao.ComplaintDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@Component
public class ComplaintWriteServiceImpl implements ComplaintWriteService {
    private final ComplaintDao complaintDao;

    public ComplaintWriteServiceImpl(ComplaintDao complaintDao) {
        this.complaintDao = complaintDao;
    }

    @Override
    public Boolean create(Complaint toCreateComplaint) {
        return this.complaintDao.save(toCreateComplaint);
    }
}
