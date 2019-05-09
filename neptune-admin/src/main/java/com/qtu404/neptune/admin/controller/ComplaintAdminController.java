package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.ComplaintFacade;
import com.qtu404.neptune.api.request.complaint.ComplaintPagingRequest;
import com.qtu404.neptune.api.response.complaint.ComplaintThinResponse;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@Api(value = "投诉-管理接口", tags = "投诉-管理接口")
@RestController
@RequestMapping("api/admin/complaint/")
public class ComplaintAdminController {
    @Reference
    private ComplaintFacade complaintFacade;

    @GetMapping("paging")
    @ApiOperation("投诉分页")
    @Acl(level = AccessLevel.ADMIN)
    public Response<Paging<ComplaintThinResponse>> paging(ComplaintPagingRequest request){
            return assertResponse(this.complaintFacade.paging(request));
    }
}
