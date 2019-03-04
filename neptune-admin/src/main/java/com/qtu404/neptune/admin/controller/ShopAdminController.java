package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.util.UserUtils;
import com.qtu404.neptune.api.facade.ShopFacade;
import com.qtu404.neptune.api.request.shop.ShopCreateRequest;
import com.qtu404.neptune.api.request.shop.ShopUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.qtu404.neptune.util.model.Response.NOT_LOGIN;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:03
 */
@Api(value = "店铺管理接口", tags = "店铺管理接口")
@RestController
@RequestMapping("api/admin/shop/")
public class ShopAdminController {
    @Reference
    private ShopFacade shopFacade;

    @PutMapping("create")
    @ApiOperation("创建店铺")
    public Response<Long> createShop(@RequestBody ShopCreateRequest request, HttpSession httpSession) {
        Long userId = UserUtils.getId(httpSession);
        if (Objects.isNull(userId)) return Response.fail("not.login", NOT_LOGIN);
        request.setUserId(userId);
        return this.shopFacade.createShop(request);
    }

    @PostMapping("update")
    @ApiOperation("店铺修改")
    public Response<Boolean> updateShopInfo(@RequestBody ShopUpdateRequest request, HttpSession httpSession) {
        Long userId = UserUtils.getId(httpSession);
        if (Objects.isNull(userId)) return Response.fail("not.login", NOT_LOGIN);
        request.setUserId(userId);
        return this.shopFacade.updateShopInfo(request);
    }
}
