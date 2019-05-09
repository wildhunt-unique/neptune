package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.ShopFacade;
import com.qtu404.neptune.api.request.shop.ShopCreateRequest;
import com.qtu404.neptune.api.request.shop.ShopGetRequest;
import com.qtu404.neptune.api.request.shop.ShopUpdateRequest;
import com.qtu404.neptune.api.response.shop.ShopThinResponse;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;
import static com.qtu404.neptune.web.common.util.RequestContext.getAccessLevel;
import static com.qtu404.neptune.web.common.util.RequestContext.getShopId;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:03
 */
@Api(value = "店铺-管理接口", tags = "店铺-管理接口")
@RestController
@RequestMapping("api/admin/shop/")
public class ShopAdminController {
    @Reference
    private ShopFacade shopFacade;

    @PutMapping("create")
    @ApiOperation("创建店铺")
    @Acl(level = AccessLevel.ADMIN)
    public Response<Long> createShop(@RequestBody ShopCreateRequest request) {
        return assertResponse(this.shopFacade.createShop(request));
    }

    @GetMapping("get")
    @ApiOperation("获得当前登录店铺信息")
    @Acl(level = AccessLevel.SHOP)
    public Response<ShopThinResponse> getShop(ShopGetRequest request){
        return assertResponse(this.shopFacade.getShopById(request));
    }

    @PostMapping("update")
    @ApiOperation("店铺修改")
    @Acl(level = AccessLevel.SHOP)
    public Response<Boolean> updateShopInfo(@RequestBody ShopUpdateRequest request) {
        if (getAccessLevel() == AccessLevel.SHOP) {
            request.setShopId(getShopId());
        }
        return assertResponse(this.shopFacade.updateShopInfo(request));
    }
}
