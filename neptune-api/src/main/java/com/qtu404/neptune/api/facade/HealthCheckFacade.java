package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.api.request.DubboCheckRequest;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午2:08
 */
public interface HealthCheckFacade {
    /**
     * dubbo服务检查
     *
     * @param request 请求参数
     * @return 是否正常
     */
    Response<String> check(DubboCheckRequest request);
}
