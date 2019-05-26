package com.qtu404.neptune.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.ShoppingCartFacade;
import com.qtu404.neptune.api.request.order.ShoppingCartCreateOrUpdateRequest;
import com.qtu404.neptune.api.request.order.ShoppingCartDetailRequest;
import com.qtu404.neptune.api.request.order.ShoppingCartShopRemoveAllRequest;
import com.qtu404.neptune.api.request.order.ShoppingFullUpdateRequest;
import com.qtu404.neptune.api.response.order.ShoppingCartDetailResponse;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;
import static com.qtu404.neptune.web.common.util.RequestContext.getUserId;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@Api(value = "购物车-web接口", tags = "购物车-web接口")
@RestController
@RequestMapping("api/web/shopping/cart/")
public class ShoppingCartWebController {
    @Reference
    private ShoppingCartFacade shoppingCartFacade;

    @ApiOperation("得到当前登录用户购物车信息")
    @GetMapping("get")
    @Acl(level = AccessLevel.USER)
    public Response<List<ShoppingCartDetailResponse>> get(ShoppingCartDetailRequest request) {
        request.setUserId(getUserId());
        return assertResponse(this.shoppingCartFacade.getShoppingCartDetail(request));
    }

    @ApiOperation("按照店铺id，清空购物车")
    @PostMapping("shop/remove/all")
    @Acl(level = AccessLevel.USER)
    public Response<Boolean> shopRemove(@RequestBody ShoppingCartShopRemoveAllRequest request) {
        request.setUserId(getUserId());
        return assertResponse(this.shoppingCartFacade.shopRemove(request));
    }

    @ApiOperation("全量更新购物信息")
    @GetMapping("full")
    @Acl(level = AccessLevel.USER)
    public Response<Boolean> fullUpdate(@RequestBody ShoppingFullUpdateRequest request) {
        request.setUserId(getUserId());
        return assertResponse(this.shoppingCartFacade.fullUpdate(request));
    }

    @ApiOperation("创建或者修改购物车商品")
    @PostMapping("create/or/update")
    @Acl(level = AccessLevel.USER)
    public Response<Boolean> createOrUpdate(@RequestBody ShoppingCartCreateOrUpdateRequest request) {
        request.setUserId(getUserId());
        return assertResponse(this.shoppingCartFacade.createOrUpdate(request));
    }
}
