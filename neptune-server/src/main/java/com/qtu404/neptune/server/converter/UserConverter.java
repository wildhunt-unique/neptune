package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.request.user.UserStatusUpdateRequest;
import com.qtu404.neptune.api.response.user.UserThinResponse;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.api.request.user.UserRegistryRequest;
import com.qtu404.neptune.api.response.user.UserInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午5:08
 */
@Mapper(componentModel = "spring")
public interface UserConverter {
    @Mappings({
            @Mapping(source = "user.name", target = "name"),
    })
    UserInfoResponse model2Response(User user);

    @Mappings({
            @Mapping(source = "request.password", target = "password"),
    })
    User request2model(UserRegistryRequest request);

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
    })
    UserThinResponse model2ThinResponse(User user);

    @Mappings({
            @Mapping(source = "request.userId", target = "id"),
    })
    User request2model(UserStatusUpdateRequest request);
}