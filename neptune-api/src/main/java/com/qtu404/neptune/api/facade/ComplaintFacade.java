package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.complaint.ComplaintCreateRequest;
import com.qtu404.neptune.api.request.complaint.ComplaintPagingRequest;
import com.qtu404.neptune.api.response.complaint.ComplaintThinResponse;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
public interface ComplaintFacade {
    Response<Long> createComplaint(ComplaintCreateRequest request);

    Response<Paging<ComplaintThinResponse>> paging(ComplaintPagingRequest request);
}
