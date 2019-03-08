package com.qtu404.neptune.server.service;

import com.google.common.collect.Maps;
import com.qtu404.neptune.domain.model.TagBinding;
import com.qtu404.neptune.domain.service.TagBindingReadService;
import com.qtu404.neptune.server.dao.TagBindingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<TagBinding> findByTagIdAndTypeCheckStatus(Long tagId, Integer type, Integer status) {
        Map<String,Object> params = Maps.newHashMap();
        params.put("tagId",tagId);
        params.put("type",type);
        params.put("status",status);
        return this.tagBindingDao.list(params);
    }
}
