package com.qtu404.neptune.web.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.HealthCheckFacade;
import com.qtu404.neptune.api.request.DubboCheckRequest;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 上午11:44
 */
@RestController
@RequestMapping("/api/health/check")
@Api(value = "可用性检查", tags = "可用性检查")
public class HealthCheckController {
    @Reference
    private HealthCheckFacade healthCheckFacade;

    @ApiOperation("打招呼")
    @GetMapping
    public String sayHello(String name) {
        if (Objects.isNull(name)) name = "";
        return "Hi " + name + "! Welcome to Neptune ";
    }

    @ApiOperation("dubbo检查")
    @PostMapping
    public String dubboCheck(@RequestBody DubboCheckRequest request) {
        return this.healthCheckFacade.check(request).getResult();
    }
}
