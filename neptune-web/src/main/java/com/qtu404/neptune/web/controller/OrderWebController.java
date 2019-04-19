package com.qtu404.neptune.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.OrderFacade;
import com.qtu404.neptune.api.request.order.OrderCreateRequest;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.util.RequestContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public Response<Long> createOrder(@RequestBody OrderCreateRequest request, HttpSession session) {
        Long userId = RequestContext.getUserId();
        request.setBuyerId(userId);
        return this.orderFacade.createOrder(request);
    }
}
