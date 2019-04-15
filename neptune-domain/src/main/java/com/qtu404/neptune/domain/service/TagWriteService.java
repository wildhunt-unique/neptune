package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Tag;
import com.qtu404.neptune.domain.model.TagBinding;

import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:46
 */
public interface TagWriteService {
    Boolean create(Tag toCreateTag);

    Boolean update(Tag toUpdateTag);

    Boolean delete(Long tagId);
}
