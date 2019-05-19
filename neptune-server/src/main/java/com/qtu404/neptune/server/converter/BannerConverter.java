package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.request.banner.BannerCreateRequest;
import com.qtu404.neptune.api.request.banner.BannerUpdateRequest;
import com.qtu404.neptune.api.response.banner.BannerThinResponse;
import com.qtu404.neptune.domain.model.Banner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@Mapper(componentModel = "spring")
public interface BannerConverter {
    Banner request2Model(BannerCreateRequest request);

    @Mappings({
            @Mapping(source = "banner.id", target = "bannerId"),
    })
    BannerThinResponse model2thinResponse(Banner banner);

    @Mappings({
            @Mapping(source = "request.bannerId", target = "id"),
    })
    Banner request2Model(BannerUpdateRequest request);
}
