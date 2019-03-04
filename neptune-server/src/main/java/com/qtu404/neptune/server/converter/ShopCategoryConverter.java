package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.request.shop.ShopCategoryCreateRequest;
import com.qtu404.neptune.domain.model.ShopCategory;
import org.mapstruct.Mapper;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:44
 */
@Mapper(componentModel = "spring")
public interface ShopCategoryConverter {
    ShopCategory request2Model(ShopCategoryCreateRequest request);
}
