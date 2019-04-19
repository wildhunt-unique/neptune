package com.qtu404.neptune.web.common.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.api.request.user.UserGetFromRedisRequest;
import com.qtu404.neptune.api.response.user.UserThinResponse;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.util.model.Response;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:22
 */
@Component
public class RequestContext {
    @Reference
    private static UserFacade userFacade;

    private static ThreadLocal<String> uuid = new ThreadLocal<>();

    public static Long getUserId() {
        String currentToken = uuid.get();
        if (StringUtils.isBlank(currentToken)) {
            // TODO: 2019/4/19 throw exception
            return null;
        }
        Response<UserThinResponse> response = userFacade.getFromRedis(UserGetFromRedisRequest.builder().key(currentToken).build());
        if (response.isSuccess() && Objects.nonNull(response.getResult())) {
            return response.getResult().getUserId();
        } else {
            return null;
        }
    }

    public static void setUuid(String tokenValue) {
        uuid.set(tokenValue);
    }
}
