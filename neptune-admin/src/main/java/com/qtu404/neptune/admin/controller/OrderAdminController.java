package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.OrderFacade;
import com.qtu404.neptune.api.request.order.OrderPagingRequest;
import com.qtu404.neptune.api.request.order.OrderUpdateRequest;
import com.qtu404.neptune.api.response.order.OrderThinResponse;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.Paging;
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
 * @date 2019/3/18 下午6:46
 */
@Api(value = "订单-管理接口", tags = "订单-管理接口")
@RestController
@RequestMapping("api/admin/order/")
public class OrderAdminController {
    @Reference
    private OrderFacade orderFacade;

    @ApiOperation("订单级更新")
    @PostMapping("update")
    @Acl(level = AccessLevel.SHOP)
    public Response<Boolean> updateOrder(@RequestBody OrderUpdateRequest request) {
        return assertResponse(orderFacade.updateOrder(request));
    }

    @ApiOperation("订单分页")
    @GetMapping("paging")
    @Acl(level = AccessLevel.SHOP)
    public Response<Paging<OrderThinResponse>> paging(OrderPagingRequest request) {
        if (getAccessLevel() == AccessLevel.SHOP) {
            request.setShopId(getShopId());
        }
        return assertResponse(this.orderFacade.paging(request));
    }
}
