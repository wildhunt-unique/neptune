package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import com.qtu404.neptune.api.request.user.*;
import com.qtu404.neptune.api.response.user.UserThinResponse;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.enums.UserTypeEnum;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.domain.service.UserReadService;
import com.qtu404.neptune.domain.service.UserWriteService;
import com.qtu404.neptune.server.converter.UserConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.ServiceException;
import com.qtu404.neptune.util.sms.SMSsender;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.api.response.user.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午4:51
 */
@Service(interfaceClass = UserFacade.class)
@Slf4j
@Component
public class UserFacadeImpl implements UserFacade {
    private final SMSsender smsSender;

    private final UserConverter userConverter;

    private final UserReadService userReadService;

    private final UserWriteService userWriteService;

    @Autowired
    public UserFacadeImpl(SMSsender smsSender, UserConverter userConverter, UserReadService userReadService, UserWriteService userWriteService) {
        this.smsSender = smsSender;
        this.userConverter = userConverter;
        this.userReadService = userReadService;
        this.userWriteService = userWriteService;
    }

    @Override
    public Response<Long> login(UserLoginRequest request) {
        return execute(request, param -> {
            request.checkParam();
            User user = this.userReadService.list(request.toMap()).stream()
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
        return execute(request, param -> this.userReadService.count(request.toMap()) == 1);
    }

    @Override
    public Response<Boolean> existUsername(ExistUsernameRequest request) {
        return execute(request, param -> this.userReadService.count(request.toMap()) == 1);
    }

    @Override
    public Response<Boolean> existEmail(ExistEmailRequest request) {
        return execute(request, param -> this.userReadService.count(request.toMap()) == 1);
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
            User existUser = this.userReadService.fetchById(request.getUserId());
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
            User user = this.userReadService.fetchById(request.getId());
            user.setAvatar(request.getAvatar());
            user.setName(request.getName());
            this.userWriteService.update(user);
            return userConverter.model2Response(user);
        });
    }

    @Override
    public Response<Long> register(UserRegistryRequest request) {
        return execute(request, param -> {
            User user = this.userConverter.request2model(request);

            // 检查手机号唯一
            if (Objects.nonNull(user.getMobile())) {
                Boolean mobileExist = AssertUtil.assertResponse(this.existPhone(ExistPhoneRequest.builder().mobile(user.getMobile()).build()));
                if (mobileExist) {
                    throw new IllegalArgumentException("mobile.already.exist");
                }
            }

            // 检查用户名唯一
            if (Objects.nonNull(user.getUsername())) {
                Boolean usernameExist = AssertUtil.assertResponse(this.existUsername(ExistUsernameRequest.builder().username(user.getUsername()).build()));
                if (usernameExist) {
                    throw new IllegalArgumentException("username.already.exist");
                }
            }

            // 检查邮箱唯一
            if (Objects.nonNull(user.getEmail())) {
                Boolean emailExist = AssertUtil.assertResponse(this.existEmail(ExistEmailRequest.builder().email(user.getEmail()).build()));
                if (emailExist) {
                    throw new IllegalArgumentException("email.already.exist");
                }
            }

            // 设置默认头像
            if (Objects.isNull(user.getAvatar())) {
                user.setAvatar(ConstantValues.DEFAULT_AVATAR);
            }

            user.setStatus(DataStatusEnum.NORMAL.getCode());
            user.setType(UserTypeEnum.CUSTOMER.getCode());
            if (this.userWriteService.addUser(user)) {
                return user.getId();
            } else {
                throw new ServiceException("user.register.fail");
            }
        });
    }

    /**
     * 用户信息分页查询
     *
     * @param request 请求参数
     * @return 分页结果
     */
    @Override
    public Response<Paging<UserThinResponse>> paging(UserPagingRequest request) {
        return execute(request, param -> {
            Map<String, Object> condition = request.toMap();
            if (Objects.nonNull(request.getUserId())) {
                condition.put("id", request.getUserId());
            }
            Paging<User> userPaging = this.userReadService.paging(condition);
            return new Paging<>(
                    userPaging.getTotal(),
                    userPaging.getData().stream()
                            .map(this.userConverter::model2ThinResponse)
                            .collect(Collectors.toList())
            );
        });
    }
}
