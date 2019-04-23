package com.qtu404.neptune.web.common.util;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.api.request.user.UserGetFromRedisRequest;
import com.qtu404.neptune.api.response.user.UserThinResponse;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.exception.AuthorizationException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:22
 */
@Component
public class RequestContext {
    @Reference
    private static UserFacade userFacade;

    private static ThreadLocal<String> uuid = new ThreadLocal<>();

    private static Map<String, UserThinResponse> uuidToUser = new ConcurrentHashMap<>();

    public static Long getUserId() throws AuthorizationException {
        String currentToken = uuid.get();
        if (StringUtils.isBlank(currentToken)) {
            throw new AuthorizationException("not.login");
        }
        UserThinResponse user = setAndGetUser(currentToken);
        if (Objects.isNull(user)) {
            throw new AuthorizationException("not.login");
        } else {
            return user.getUserId();
        }
    }

    private static UserThinResponse setAndGetUser(String currentToken) {
        UserThinResponse user = null;//uuidToUser.get(currentToken);
        if (Objects.nonNull(user)) {
            return user;
        } else {
            Response<UserThinResponse> response = userFacade.getFromRedis(UserGetFromRedisRequest.builder().key(currentToken).build());
            if (response.isSuccess() && Objects.nonNull(response.getResult())) {
                //uuidToUser.put(currentToken, response.getResult());
                return response.getResult();
            } else {
                return null;
            }
        }
    }

    public static void setUuid(String tokenValue) {
        uuid.set(tokenValue);
    }
}
