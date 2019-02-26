package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.server.converter.UserConverter;
import com.qtu404.neptune.server.dao.UserDao;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.ServiceException;
import com.qtu404.neptune.util.sms.SMSsender;
import com.qut404.neptune.api.facade.user.UserReadFacade;
import com.qut404.neptune.api.request.user.*;
import com.qut404.neptune.api.response.user.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午4:51
 */
@Service(interfaceClass = UserReadFacade.class)
@Slf4j
@Component
public class UserFacadeImpl implements UserReadFacade {
    private final UserDao userDao;

    private final SMSsender smsSender;

    private final UserConverter userConverter;

    @Autowired
    public UserFacadeImpl(UserDao userDao, SMSsender smsSender, UserConverter userConverter) {
        this.userDao = userDao;
        this.smsSender = smsSender;
        this.userConverter = userConverter;
    }

    @Override
    public Response<Long> login(UserLoginRequest request) {
        return execute(request, param -> {
            request.checkParam();
            User user = this.userDao.list(request.toMap()).stream()
                    .findFirst()
                    .orElse(null);
            if (Objects.isNull(user)) {
                throw new IllegalArgumentException("username.or.password.error");
            } else {
                return user.getId();
            }
        });
    }

    @Override
    public Response<Boolean> existPhone(ExistPhoneRequest request) {
        return execute(request, param -> this.userDao.count(request.toMap()) == 1);
    }

    @Override
    public Response<Boolean> existNickname(ExistNicknameRequest request) {
        return execute(request, param -> this.userDao.count(request.toMap()) == 1);
    }

    @Override
    public Response<Boolean> existEmail(ExistEmailRequest request) {
        return execute(request, param -> this.userDao.count(request.toMap()) == 1);
    }

    @Override
    public Response<Boolean> sendRegisterVerificationSMS(SendRegisterVerificationSmsRequest request) {
        return execute(request, param -> {
            try {
                smsSender.sendSmsMessage(request.getMobile(), request.getCode());
            } catch (ClientException e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        });
    }

    @Override
    public Response<UserInfoResponse> findSingleUserInfoById(FindSingleUserInfoRequest request) {
        return execute(request, param -> {
            User existUser = this.userDao.fetch(request.getUserId());
            if (Objects.isNull(existUser)) {
                throw new ServiceException("user.not.exist");
            } else {
                return this.userConverter.model2Response(existUser);
            }
        });
    }

    @Override
    public Response<UserInfoResponse> modifyUserInfo(UserModifyInfoRequest request) {
        return execute(request, param -> {
            User user = this.userDao.fetch(request.getId());
            user.setAvatar(request.getAvatar());
            user.setName(request.getName());
            this.userDao.update(user);
            FindSingleUserInfoRequest singleUserInfoRequest = new FindSingleUserInfoRequest();
            singleUserInfoRequest.setUserId(request.getId());
            return this.findSingleUserInfoById(singleUserInfoRequest).getResult();
        });
    }

    @Override
    public Response<Long> register(UserRegistryRequest request) {
        return execute(request, param -> {
            request.checkParam();
            User user = this.userConverter.request2model(request);
            user.setStatus(DataStatusEnum.NORMAL.getCode());
            user.setAvatar(ConstantValues.DEFAULT_AVATAR);
            if (this.userDao.save(user)) {
                return user.getId();
            } else {
                throw new ServiceException("user.register.fail");
            }
        });
    }
}
