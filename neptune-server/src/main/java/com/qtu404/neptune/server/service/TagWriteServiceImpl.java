package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Tag;
import com.qtu404.neptune.domain.service.TagWriteService;
import com.qtu404.neptune.server.dao.TagDao;
import com.qtu404.neptune.server.manager.TagManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:45
 */
@Service
public class TagWriteServiceImpl implements TagWriteService {
    private final TagDao tagDao;
    private final TagManager tagManager;

    @Autowired
    public TagWriteServiceImpl(TagDao tagDao, TagManager tagManager) {
        this.tagDao = tagDao;
        this.tagManager = tagManager;
    }

    @Override
    public Boolean create(Tag toCreateTag) {
        return this.tagDao.save(toCreateTag);
    }

    @Override
    public Boolean update(Tag toUpdateTag) {
        return this.tagDao.update(toUpdateTag);
    }

    @Override
    public Boolean delete(Long tagId) {
        return this.tagManager.deleteTag(tagId);
    }
}
