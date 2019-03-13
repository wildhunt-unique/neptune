package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.TagBinding;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午2:56
 */
public interface TagBindingReadService {
    List<TagBinding> findByTagIdAndTypeCheckStatus(Long tagId, Integer type, Integer status);

    List<TagBinding> findByTargetIdAndTypeCheckStatus(Long targetId, Integer type, Integer status);
}
