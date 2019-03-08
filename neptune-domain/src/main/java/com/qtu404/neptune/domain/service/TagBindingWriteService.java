package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.TagBinding;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午2:57
 */
public interface TagBindingWriteService {
    Integer batchCreate(List<TagBinding> toCreateTagBinding);

    Integer batchSetStatusByTagIdAndType(Long tagId, Integer type, Integer status);
}
