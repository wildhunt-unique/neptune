package com.qtu404.neptune.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.ComplaintFacade;
import com.qtu404.neptune.api.request.complaint.ComplaintCreateRequest;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;
import static com.qtu404.neptune.web.common.util.RequestContext.getUserId;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@Api(value = "投诉-web接口", tags = "投诉-web接口")
@RestController
@RequestMapping("api/web/complaint/")
public class ComplaintWebController {
    @Reference
    private ComplaintFacade complaintFacade;

    @ApiOperation("创建投诉")
    @PostMapping("create")
    @Acl(level= AccessLevel.USER)
    public Response<Long> createComplaint(@RequestBody ComplaintCreateRequest request){
        request.setUserId(getUserId());
        return assertResponse(this.complaintFacade.createComplaint(request));
    }
}
