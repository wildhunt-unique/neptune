package com.qtu404.neptune.web.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.ShopFacade;
import com.qtu404.neptune.api.request.shop.ShopPagingRequest;
import com.qtu404.neptune.api.response.shop.ShopThinResponse;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/8 下午3:09
 */
@Api(value = "店铺-通用接口", tags = "店铺-通用接口")
@RestController
@RequestMapping("api/common/shop/")
public class ShopCommonController {
    @Reference
    private ShopFacade shopFacade;

    @GetMapping("paging")
    @ApiOperation("店铺分页查询")
    public Response<Paging<ShopThinResponse>> shopPaging(ShopPagingRequest request){
        return this.shopFacade.shopPaging(request);
    }
}