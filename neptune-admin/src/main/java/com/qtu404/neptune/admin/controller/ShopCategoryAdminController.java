package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.request.shop.ShopCategoryUpdateRequest;
import com.qtu404.neptune.api.request.shop.ShopCategoryCreateRequest;
import com.qtu404.neptune.api.facade.ShopCategoryFacade;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:45
 */
@Api(value = "店铺类目-管理接口", tags = "店铺类目-管理接口")
@RestController
@RequestMapping("api/admin/shop/category")
public class ShopCategoryAdminController {
    @Reference
    private ShopCategoryFacade shopCategoryFacade;

    @ApiOperation("创建店铺类目")
    @PutMapping("create")
    @Acl(level = AccessLevel.SHOP)
    public Response<Long> create(@RequestBody ShopCategoryCreateRequest request) {
        return assertResponse(this.shopCategoryFacade.create(request));
    }

    @ApiOperation("修改店铺类目")
    @PostMapping("update")
    @Acl(level = AccessLevel.SHOP)
    public Response<Boolean> update(@RequestBody ShopCategoryUpdateRequest request){
        return assertResponse(this.shopCategoryFacade.update(request));
    }

}
