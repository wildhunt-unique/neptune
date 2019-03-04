package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.base.Throwables;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.api.facade.HealthCheckFacade;
import com.qtu404.neptune.api.request.DubboCheckRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午2:08
 */
@Component
@Service(interfaceClass = HealthCheckFacade.class)
@Slf4j
public class HealthCheckFacadeImpl implements HealthCheckFacade {
    @Override
    public Response<String> check(DubboCheckRequest request) {
        try {
            request.checkParam();
            String name = request.getName();
            return Response.success("Hi! " + name + ", dubbo is on service");
        } catch (Exception e) {
            log.error("failed.to with request:{},cause:{}", request, Throwables.getStackTraceAsString(e));
            return Response.fail(" fail");
        }
    }
}
