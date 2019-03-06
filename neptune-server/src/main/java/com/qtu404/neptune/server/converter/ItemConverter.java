package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.request.item.ItemCreateRequest;
import com.qtu404.neptune.api.request.item.ItemUpdateRequest;
import com.qtu404.neptune.api.response.item.ItemThinResponse;
import com.qtu404.neptune.domain.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:46
 */
@Mapper(componentModel = "spring")
public interface ItemConverter {
    Item request2Model(ItemCreateRequest request);

    @Mappings(
            @Mapping(source = "request.itemId", target = "id")
    )
    Item request2Model(ItemUpdateRequest request);

    @Mappings(
            @Mapping(source = "item.id", target = "itemId")
    )
    ItemThinResponse model2ThinResponse(Item item);
}
