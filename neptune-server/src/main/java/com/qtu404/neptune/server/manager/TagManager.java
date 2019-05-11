package com.qtu404.neptune.server.manager;

import com.qtu404.neptune.server.dao.TagBindingDao;
import com.qtu404.neptune.server.dao.TagDao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/15
 */
@Component
public class TagManager {
    private final TagDao tagDao;
    private final TagBindingDao tagBindingDao;

    public TagManager(TagDao tagDao, TagBindingDao tagBindingDao) {
        this.tagDao = tagDao;
        this.tagBindingDao = tagBindingDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTag(Long tagId){
        this.tagDao.remove(tagId);
        this.tagBindingDao.batchRemoveByTagId(tagId);
        return Boolean.TRUE;
    }
}
