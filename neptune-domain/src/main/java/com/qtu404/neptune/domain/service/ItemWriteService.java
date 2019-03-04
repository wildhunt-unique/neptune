package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Item;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:27
 */
public interface ItemWriteService {
    Boolean createItem(Item toCreateItem);

    Boolean update(Item toUpdateItem);
}
