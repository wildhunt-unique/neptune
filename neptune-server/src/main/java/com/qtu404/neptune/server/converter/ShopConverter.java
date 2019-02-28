package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.domain.model.Shop;
import com.qut404.neptune.api.request.shop.ShopCreateRequest;
import com.qut404.neptune.api.request.shop.ShopUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:17
 */
@Mapper(componentModel = "spring")
public interface ShopConverter {
    public Shop createRequest2Model(ShopCreateRequest request);

    @Mapping(source = "shopId", target = "id")
    public Shop updateRequest2Model(ShopUpdateRequest request);
}
