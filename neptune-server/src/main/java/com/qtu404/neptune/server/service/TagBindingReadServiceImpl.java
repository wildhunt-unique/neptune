package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.service.TagBindingReadService;
import com.qtu404.neptune.server.dao.TagBindingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午2:56
 */
@Service
public class TagBindingReadServiceImpl implements TagBindingReadService {
    private final TagBindingDao tagBindingDao;

    @Autowired
    public TagBindingReadServiceImpl(TagBindingDao tagBindingDao) {
        this.tagBindingDao = tagBindingDao;
    }
}
