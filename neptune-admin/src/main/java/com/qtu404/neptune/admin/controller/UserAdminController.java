package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.api.request.user.UserPagingRequest;
import com.qtu404.neptune.api.request.user.UserStatusUpdateRequest;
import com.qtu404.neptune.api.response.user.UserThinResponse;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/13 上午10:15
 */
@Api(value = "用户-管理接口", tags = "用户-管理接口")
@RestController
@RequestMapping("api/admin/user/")
public class UserAdminController {
    @Reference
    private UserFacade userFacade;

    @ApiOperation("用户分页")
    @GetMapping("paging")
    public Response<Paging<UserThinResponse>> userPaging(UserPagingRequest request){
        return assertResponse(this.userFacade.paging(request));
    }


    @ApiOperation("用户禁用/启用")
    @PutMapping("update/status")
    public Response<Boolean> userStatus(@RequestBody UserStatusUpdateRequest request){
        return assertResponse(this.userFacade.updateStatus(request));
    }
}
