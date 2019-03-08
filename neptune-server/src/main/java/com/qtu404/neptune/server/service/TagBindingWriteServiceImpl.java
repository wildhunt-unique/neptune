package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.TagBinding;
import com.qtu404.neptune.domain.service.TagBindingWriteService;
import com.qtu404.neptune.server.dao.TagBindingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        if (CollectionUtils.isEmpty(toCreateTagBinding)) {
            return 0;
        }
        return tagBindingDao.save(toCreateTagBinding);
    }

    @Override
    public Integer batchSetStatusByTargetIdAndType(Long targetId, Integer type, Integer status) {
        return this.tagBindingDao.batchSetStatusByTargetIdAndType(targetId, type, status);
    }

    @Override
    public Integer batchSetStatusByTagIdAndType(Long tagId, Integer type, Integer status) {
        return this.tagBindingDao.batchSetStatusByTagIdAndType(tagId, type, status);
    }
}
