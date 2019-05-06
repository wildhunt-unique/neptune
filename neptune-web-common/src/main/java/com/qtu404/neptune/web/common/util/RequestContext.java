package com.qtu404.neptune.web.common.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.api.request.user.UserGetFromRedisRequest;
import com.qtu404.neptune.api.response.user.UserMetaData;
import com.qtu404.neptune.util.model.exception.AuthorizationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.qtu404.neptune.util.model.AssertUtil.sortResponse;

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
        return assertUserExist(getCurrentUser()).getUserId();
    }

    public static Integer getAccessLevel() {
        return assertUserExist(getCurrentUser()).getLevel();
    }

    public static Long getShopId() {
        return assertUserExist(getCurrentUser()).getShopId();
    }

    private static UserMetaData getCurrentUser() {
        UserMetaData user = uuidToUser.get();
        return Objects.nonNull(user) ?
                user :
                Objects.nonNull(uuid.get()) ?
                        getAndSetUser() :
                        null;
    }

    private static UserMetaData getAndSetUser() {
        UserMetaData user = sortResponse(userFacade.getFromRedis(UserGetFromRedisRequest.builder().key(uuid.get()).build()));
        uuidToUser.set(user);
        return user;
    }

    private static UserMetaData assertUserExist(UserMetaData userMetaData) {
        if (Objects.isNull(userMetaData)) {
            throw new AuthorizationException("not.login");
        }
        return userMetaData;
    }

    public static void setUuid(String tokenValue) {
        uuid.set(tokenValue);
        uuidToUser.set(null);
    }
}
