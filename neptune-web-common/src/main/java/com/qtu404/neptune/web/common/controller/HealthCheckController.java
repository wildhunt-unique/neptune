package com.qtu404.neptune.web.common.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 上午11:44
 */
@RestController
@RequestMapping("/api/health/check")
@Api(value = "可用性检查",tags = "可用性检查")
public class HealthCheckController {
    @ApiOperation("打招呼")
    @GetMapping
    public String sayHello(String name) {
        if (Objects.isNull(name)) name = "";
        return "Hi " + name + "! Welcome to Neptune ";
    }
}
