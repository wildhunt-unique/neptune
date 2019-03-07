package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.TagBinding;
import com.qtu404.neptune.domain.service.TagBindingWriteService;
import com.qtu404.neptune.server.dao.TagBindingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午2:57
 */
@Service
public class TagBindingWriteServiceImpl implements TagBindingWriteService {
    private final TagBindingDao tagBindingDao;

    @Autowired
    public TagBindingWriteServiceImpl(TagBindingDao tagBindingDao) {
        this.tagBindingDao = tagBindingDao;
    }

    @Override
    public Integer batchCreate(List<TagBinding> toCreateTagBinding) {
        return tagBindingDao.save(toCreateTagBinding);
    }
}
