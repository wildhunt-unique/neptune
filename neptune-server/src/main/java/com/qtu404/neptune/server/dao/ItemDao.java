package com.qtu404.neptune.server.dao;

import com.google.common.collect.Lists;
import com.qtu404.neptune.domain.model.Item;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:22
 */
@Repository
public class ItemDao extends MyBatisDAO<Item> {
    public List<Item> findByCategoryIds(List<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return Lists.newArrayList();
        } else {
            return this.sqlSession.selectList(this.sqlId("findByCategoryIds"),categoryIds);
        }
    }
}
