package com.qtu404.neptune.web.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.request.item.ItemGetRequest;
import com.qtu404.neptune.api.facade.ItemFacade;
import com.qtu404.neptune.api.response.item.ItemThinResponse;
import com.qtu404.neptune.util.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/28 上午10:32
 */
@Api(value = "评价-通用接口", tags = "评价-通用接口")
@RestController
@RequestMapping("api/common/item/")
public class ItemCommonController {
    @Reference
    private ItemFacade itemFacade;

    @GetMapping("get")
    @ApiOperation("根据id获取商品信息")
    public Response<ItemThinResponse> getItemById(ItemGetRequest request) {
        return this.itemFacade.getItemById(request);
    }
}
