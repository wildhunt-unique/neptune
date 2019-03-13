package com.qtu404.neptune.server.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qtu404.neptune.domain.model.Tag;
import com.qtu404.neptune.domain.service.TagReadService;
import com.qtu404.neptune.server.dao.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:45
 */
@Service
public class TagReadServiceImpl implements TagReadService {
    private final TagDao tagDao;

    @Autowired
    public TagReadServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> list() {
        return tagDao.list();
    }

    @Override
    public List<Tag> findByType(Integer type) {
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("type", type);
        return this.tagDao.list(condition);
    }

    @Override
    public List<Tag> findByIds(List<Long> tagIds) {
        if (CollectionUtils.isEmpty(tagIds)) {
            return Lists.newArrayList();
        }
        return this.tagDao.fetch(tagIds);
    }
}
