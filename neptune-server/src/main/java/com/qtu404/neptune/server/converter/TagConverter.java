package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.request.tag.TagCreateRequest;
import com.qtu404.neptune.api.response.tag.TagThinResponse;
import com.qtu404.neptune.domain.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:44
 */
@Mapper(componentModel = "spring")
public interface TagConverter {
    Tag request2Model(TagCreateRequest request);

    @Mappings(
            @Mapping(source = "id", target = "tagId")
    )
    TagThinResponse model2ThinResponse(Tag tag);
}
