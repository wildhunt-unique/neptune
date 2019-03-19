package com.qtu404.neptune.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.OrderFacade;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
