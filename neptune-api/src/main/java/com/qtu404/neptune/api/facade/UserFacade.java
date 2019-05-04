package com.qtu404.neptune.api.facade;

import com.qtu404.neptune.api.request.user.*;
import com.qtu404.neptune.api.response.user.UserMetaData;
import com.qtu404.neptune.api.response.user.UserThinResponse;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:15
 */
public interface UserFacade {
    Response<String> login(UserLoginRequest request);

    Response<UserMetaData> getFromRedis(UserGetFromRedisRequest request);

    Response<Boolean> existPhone(ExistPhoneRequest request);

    Response<Boolean> existUsername(ExistUsernameRequest request);

    Response<Boolean> existEmail(ExistEmailRequest request);

    Response<Boolean> sendRegisterSms(UserSendRegisterSmsRequest request);

    Response<UserThinResponse> findSingleUserInfoById(FindSingleUserRequest request);

    Response<UserThinResponse> modifyUserInfo(UserModifyRequest request);

    Response<Long> register(UserRegistryRequest request);

    Response<Paging<UserThinResponse>> paging(UserPagingRequest request);

    Response<Boolean> updateStatus(UserStatusUpdateRequest request);

    Response<Boolean> sendLoginSms(UserSendLoginSmsRequest request);

    Response<Long> smsLogin(UserSmsLoginRequest request);
}
