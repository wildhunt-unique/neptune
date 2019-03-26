package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.request.comment.CommentCreateRequest;
import com.qtu404.neptune.api.response.comment.CommentThinResponse;
import com.qtu404.neptune.domain.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:56
 */
@Mapper(componentModel = "spring")
public interface CommentConverter {

    Comment request2Model(CommentCreateRequest request);

    @Mappings({
            @Mapping(source = "comment.id", target = "commentId"),
            @Mapping(source = "comment.targetId", target = "orderId")
    })
    CommentThinResponse model2ThinResponse(Comment comment);
}
