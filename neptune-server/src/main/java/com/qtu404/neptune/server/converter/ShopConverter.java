package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.response.shop.ShopDetailResponse;
import com.qtu404.neptune.api.response.shop.ShopThinResponse;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.api.request.shop.ShopCreateRequest;
import com.qtu404.neptune.api.request.shop.ShopUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:17
 */
@Mapper(componentModel = "spring")
public interface ShopConverter {
    Shop createRequest2Model(ShopCreateRequest request);

    @Mapping(source = "shopId", target = "id")
    Shop updateRequest2Model(ShopUpdateRequest request);

    @Mapping(source = "id", target = "shopId")
    ShopDetailResponse model2DetailResponse(Shop shop);

    @Mapping(source = "id", target = "shopId")
    ShopThinResponse model2ThinResponse(Shop shop);
}
