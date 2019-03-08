package com.qtu404.neptune.server.dao;

import com.google.common.collect.Maps;
import com.qtu404.neptune.domain.model.TagBinding;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午2:55
 */
@Repository
public class TagBindingDao extends MyBatisDAO<TagBinding> {
    public Integer batchSetStatusByTagIdAndType(Long tagId, Integer type, Integer status) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("tagId", tagId);
        params.put("type", type);
        params.put("status", status);
        return this.sqlSession.update(this.sqlId("batchSetStatus"), params);
    }

    public Integer batchSetStatusByTargetIdAndType(Long targetId, Integer type, Integer status) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("targetId", targetId);
        params.put("type", type);
        params.put("status", status);
        return this.sqlSession.update(this.sqlId("batchSetStatus"), params);
    }

}
