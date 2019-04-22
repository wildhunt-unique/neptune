package com.qtu404.neptune.web.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.OrderFacade;
import com.qtu404.neptune.api.request.order.OrderDetailRequest;
import com.qtu404.neptune.api.response.order.OrderDetailResponse;
import com.qtu404.neptune.util.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;

@Api(value = "订单-通用接口", tags = "订单-通用接口")
@RestController
@RequestMapping("api/common/order/")
public class OrderCommonController {
    @Reference
    private OrderFacade orderFacade;

    @ApiOperation("查看订单详情")
    @GetMapping("get/detail")
    public Response<OrderDetailResponse> getOrderDetail(OrderDetailRequest request) {
        return assertResponse(this.orderFacade.getOrderDetail(request));
    }
}
