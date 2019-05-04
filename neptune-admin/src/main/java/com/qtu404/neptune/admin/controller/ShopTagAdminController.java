package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.ShopTagFacade;
import com.qtu404.neptune.api.request.tag.TagCreateRequest;
import com.qtu404.neptune.api.request.tag.TagDeleteRequest;
import com.qtu404.neptune.api.request.tag.TagUpdateRequest;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.Acl;
import com.qtu404.neptune.web.common.util.RequestContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:49
 */
@Api(value = "店铺标签-管理接口", tags = "店铺标签-管理接口")
@RestController
@RequestMapping("api/admin/tag/")
public class ShopTagAdminController {
    @Reference
    private ShopTagFacade shopTagFacade;

    @PutMapping("create")
    @ApiOperation("创建店铺标签")
    @Acl(level= AccessLevel.ADMIN)
    public Response<Long> create(@RequestBody TagCreateRequest request) {
        RequestContext.getUserId();// TODO: 2019/3/7 check user
        return assertResponse(this.shopTagFacade.create(request));
    }

    @PostMapping("update")
    @ApiOperation("修改店铺标签")
    @Acl(level= AccessLevel.ADMIN)
    public Response<Boolean> update(@RequestBody TagUpdateRequest request) {
        return assertResponse(this.shopTagFacade.update(request));
    }

    @PostMapping("delete")
    @ApiOperation("删除标签，物理删除")
    @Acl(level= AccessLevel.ADMIN)
    public Response<Boolean> delete(@RequestBody TagDeleteRequest request) {
        return assertResponse(this.shopTagFacade.delete(request));
    }
}
