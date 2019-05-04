package com.qtu404.neptune.web.common.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.api.request.user.UserGetFromRedisRequest;
import com.qtu404.neptune.api.response.user.UserMetaData;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.exception.AuthorizationException;
import org.springframework.stereotype.Component;

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

    private static ThreadLocal<UserMetaData> uuidToUser = new ThreadLocal<>();

    public static Long getUserId() {
        UserMetaData user = setAndGetUser();
        if (Objects.isNull(user)) {
            throw new AuthorizationException("not.login");
        } else {
            return user.getUserId();
        }
    }

    public static Integer getAccessLevel(){
        UserMetaData user = setAndGetUser();
        if (Objects.isNull(user)) {
            throw new AuthorizationException("not.login");
        } else {
            return user.getLevel();
        }
    }

    public static Long getShopId(){
        UserMetaData user = setAndGetUser();
        if (Objects.isNull(user)) {
            throw new AuthorizationException("not.login");
        } else {
            return user.getShopId();
        }
    }

    private static UserMetaData setAndGetUser() {
        UserMetaData user = uuidToUser.get();
        if (Objects.nonNull(user)) {
            return user;
        } else {
            String token = uuid.get();
            if (Objects.isNull(token)){
                return null;
            }
            Response<UserMetaData> response = userFacade.getFromRedis(UserGetFromRedisRequest.builder().key(uuid.get()).build());
            if (response.isSuccess() && Objects.nonNull(response.getResult())) {
                uuidToUser.set(response.getResult());
                return response.getResult();
            } else {
                return null;
            }
        }
    }

    public static void setUuid(String tokenValue) {
        uuid.set(tokenValue);
        uuidToUser.set(null);
    }
}
