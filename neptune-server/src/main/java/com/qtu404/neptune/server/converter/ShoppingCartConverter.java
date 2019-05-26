package com.qtu404.neptune.server.converter;
import com.qtu404.neptune.api.request.order.ShoppingCartCreateOrUpdateRequest;
import com.qtu404.neptune.api.response.order.ShoppingCartThinResponse;
import com.qtu404.neptune.domain.model.Item;
import com.qtu404.neptune.domain.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@Mapper(componentModel = "spring")
public interface ShoppingCartConverter {
    @Mapping(source = "request.id", target = "shoppingCartId")
    ShoppingCartThinResponse model2ThinResponse(ShoppingCart request);

    ShoppingCart request2Model(ShoppingCartCreateOrUpdateRequest request);
}
