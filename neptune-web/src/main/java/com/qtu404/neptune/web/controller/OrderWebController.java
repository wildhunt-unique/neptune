package com.qtu404.neptune.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.OrderFacade;
import com.qtu404.neptune.api.request.order.OrderCancelRequest;
import com.qtu404.neptune.api.request.order.OrderCreateRequest;
import com.qtu404.neptune.api.request.order.OrderPagingRequest;
import com.qtu404.neptune.api.request.order.OrderPayRequest;
import com.qtu404.neptune.api.response.order.OrderThinResponse;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;
import static com.qtu404.neptune.web.common.util.RequestContext.getUserId;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:55
 */
@Api(value = "订单-web接口", tags = "订单-web接口")
@RestController
@RequestMapping("api/web/order/")
public class OrderWebController {
    @Reference
    private OrderFacade orderFacade;

    @ApiOperation("订单创建")
    @PostMapping("create")
    @Acl(level = AccessLevel.USER)
    public Response<Long> createOrder(@RequestBody OrderCreateRequest request) {
        request.setBuyerId(getUserId());
        return assertResponse(this.orderFacade.createOrder(request));
    }

    @ApiOperation("支付订单")
    @PostMapping("pay")
    @Acl(level = AccessLevel.USER)
    public Response<Boolean> payOrder(@RequestBody OrderPayRequest request) {
        request.setBuyerId(getUserId());
        return assertResponse(this.orderFacade.payOrder(request));
    }

    @ApiOperation("查询当前登录用户的订单")
    @GetMapping("list")
    @Acl(level = AccessLevel.USER)
    public Response<Paging<OrderThinResponse>> list(OrderPagingRequest request) {
        request.setBuyerId(getUserId());
        return assertResponse(this.orderFacade.paging(request));
    }

    @ApiOperation("取消订单")
    @PostMapping("cancel")
    @Acl(level = AccessLevel.USER)
    public Response<Boolean> cancel(@RequestBody OrderCancelRequest request) {
        request.setUserId(getUserId());
        return assertResponse(this.orderFacade.cancel(request));
    }
}
