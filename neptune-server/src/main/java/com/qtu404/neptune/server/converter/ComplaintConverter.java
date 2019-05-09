package com.qtu404.neptune.server.converter;

import com.qtu404.neptune.api.request.complaint.ComplaintCreateRequest;
import com.qtu404.neptune.api.response.complaint.ComplaintThinResponse;
import com.qtu404.neptune.domain.model.Complaint;
import org.mapstruct.Mapper;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@Mapper(componentModel = "spring")
public interface ComplaintConverter {
    Complaint request2Model(ComplaintCreateRequest request);

    ComplaintThinResponse model2ThinResponse(Complaint complaint);
}
