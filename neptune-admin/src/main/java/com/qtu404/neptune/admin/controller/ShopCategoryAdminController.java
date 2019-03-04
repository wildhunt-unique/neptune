package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.request.shop.ShopCategoryCreateRequest;
import com.qtu404.neptune.api.facade.ShopCategoryFacade;
import com.qtu404.neptune.util.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:45
 */
@Api(value = "店铺类目管理接口", tags = "店铺类目管理接口")
@RestController
@RequestMapping("api/admin/shop/category")
public class ShopCategoryAdminController {
    @Reference
    private ShopCategoryFacade shopCategoryFacade;

    @ApiOperation("创建店铺类目")
    @PutMapping("create")
    public Response<Long> create(@RequestBody ShopCategoryCreateRequest request) {
        return this.shopCategoryFacade.create(request);
    }

}
