package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.google.common.base.Throwables;
import com.qtu404.neptune.api.request.user.*;
import com.qtu404.neptune.api.response.user.UserMetaData;
import com.qtu404.neptune.api.response.user.UserThinResponse;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.enums.UserTypeEnum;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.domain.service.UserReadService;
import com.qtu404.neptune.domain.service.UserWriteService;
import com.qtu404.neptune.server.converter.UserConverter;
import com.qtu404.neptune.server.converter.UserMetaDataConverter;
import com.qtu404.neptune.util.model.*;
import com.qtu404.neptune.util.model.exception.ServiceException;
import com.qtu404.neptune.util.redis.RedisManager;
import com.qtu404.neptune.util.sms.SMSsender;
import com.qtu404.neptune.api.facade.UserFacade;
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
    private final RedisManager redisManager;
    private final SMSsender smsSender;
    private final UserConverter userConverter;
    private final UserReadService userReadService;
    private final UserWriteService userWriteService;

    @Autowired
    public UserFacadeImpl(SMSsender smsSender, UserConverter userConverter, UserReadService userReadService, UserWriteService userWriteService, RedisManager redisUtil) {
        this.smsSender = smsSender;
        this.userConverter = userConverter;
        this.userReadService = userReadService;
        this.userWriteService = userWriteService;
        this.redisManager = redisUtil;
    }

    /**
     * 使用账号密码登陆
     *
     * @param request 账号密码
     * @return 是否成功
     */
    @Override
    public Response<String> login(UserLoginRequest request) {
        return execute(request, param -> {
            request.checkParam();
            User user = this.userReadService.list(request.toMap()).stream()
                    .findFirst()
                    .orElse(null);
            if (Objects.isNull(user)) {
                throw new IllegalArgumentException("username.or.password.error");
            } else if (user.getStatus().equals(DataStatusEnum.FREEZE.getCode())) {
                throw new ServiceException("user.freeze");
            } else {
                String uuid = MyJSON.md5(user.getId().toString());
                this.redisManager.set(RedisManager.Util.getKey(ConstantValues.UUID_PREFIX, uuid), MyJSON.toJSON(UserMetaDataConverter.model2Response(user)));
                return uuid;
            }
        });
    }

    /**
     * 根据token读取会话信息
     *
     * @param request token
     * @return 用户信息
     */
    @Override
    public Response<UserMetaData> getFromRedis(UserGetFromRedisRequest request) {
        return execute(request, param -> {
            String userMetaDataJson = this.redisManager.get(RedisManager.Util.getKey(ConstantValues.UUID_PREFIX, request.getKey()));
            UserMetaData userMetaData = null;
            if (!StringUtils.isBlank(userMetaDataJson)) {


                userMetaData = JSONObject.parseObject(userMetaDataJson, UserMetaData.class);
            }
            return userMetaData;
        });
    }

    /**
     * 判断手机号是否已存在
     *
     * @param request 手机号
     * @return 是否存在
     */
    @Override
    public Response<Boolean> existPhone(ExistPhoneRequest request) {
        return execute(request, param -> this.userReadService.count(request.toMap()) == 1);
    }

    /**
     * 判断用户名是否已存在
     *
     * @param request 用户名
     * @return 是否已存在
     */
    @Override
    public Response<Boolean> existUsername(ExistUsernameRequest request) {
        return execute(request, param -> this.userReadService.count(request.toMap()) == 1);
    }

    /**
     * 判断邮箱是否已存在
     *
     * @param request 邮箱地址
     * @return 是否已存在
     */
    @Override
    public Response<Boolean> existEmail(ExistEmailRequest request) {
        return execute(request, param -> this.userReadService.count(request.toMap()) == 1);
    }

    /**
     * 发送注册验证码
     *
     * @param request 手机号
     * @return 是否成功
     */
    @Override
    public Response<Boolean> sendRegisterSms(UserSendRegisterSmsRequest request) {
        return execute(request, param -> {
            try {
                String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
                // TODO: 2019/4/24 set expire time
                log.info("send.code:{}", code);
                this.redisManager.set(RedisManager.Util.getKey(ConstantValues.REGISTER_SMS_PREFIX, request.getMobile()), code);
                smsSender.sendSmsMessage(request.getMobile(), code);
            } catch (ClientException e) {
                log.error("fail.to.send.register.sms.by:{}.cause:{}", request, Throwables.getStackTraceAsString(e));
                throw new ServiceException("sms.send.fail");
            }
            return Boolean.TRUE;
        });
    }

    /**
     * 通过用户id，得到用户信息
     *
     * @param request 用户id
     * @return 用户信息
     */
    @Override
    public Response<UserThinResponse> findSingleUserInfoById(FindSingleUserRequest request) {
        return execute(request, param -> {
            User existUser = this.userReadService.fetchById(request.getUserId());
            if (Objects.isNull(existUser)) {
                throw new ServiceException("user.not.exist");
            } else {
                return this.userConverter.model2ThinResponse(existUser);
            }
        });
    }

    /**
     * 修改用户信息
     *
     * @param request 用户信息
     * @return 是否成功
     */
    @Override
    public Response<UserThinResponse> modifyUserInfo(UserModifyRequest request) {
        return execute(request, param -> {
            User user = this.userReadService.fetchById(request.getId());
            user.setAvatar(request.getAvatar());
            user.setName(request.getName());
            this.userWriteService.update(user);
            return userConverter.model2ThinResponse(user);
        });
    }

    /**
     * 注册
     */
    @Override
    public Response<Long> register(UserRegistryRequest request) {
        return execute(request, param -> {
            User user = this.userConverter.request2model(request);

            // 检查验证码
            String verifyCode = this.redisManager.get(RedisManager.Util.getKey(ConstantValues.REGISTER_SMS_PREFIX, request.getMobile()));
            if (Objects.isNull(verifyCode) || !verifyCode.equals(request.getCode())) {
                throw new ServiceException("verify.code.error");
            }

            // 检查手机号唯一
            if (Objects.nonNull(user.getMobile())) {
                Boolean mobileExist = AssertUtil.assertResponseResult(this.existPhone(ExistPhoneRequest.builder().mobile(user.getMobile()).build()));
                if (mobileExist) {
                    throw new IllegalArgumentException("mobile.already.exist");
                }
            }

            // 检查用户名唯一
            if (Objects.nonNull(user.getUsername())) {
                Boolean usernameExist = AssertUtil.assertResponseResult(this.existUsername(ExistUsernameRequest.builder().username(user.getUsername()).build()));
                if (usernameExist) {
                    throw new IllegalArgumentException("username.already.exist");
                }
            }

            // 检查邮箱唯一
            if (Objects.nonNull(user.getEmail())) {
                Boolean emailExist = AssertUtil.assertResponseResult(this.existEmail(ExistEmailRequest.builder().email(user.getEmail()).build()));
                if (emailExist) {
                    throw new IllegalArgumentException("email.already.exist");
                }
            }

            // 设置默认昵称
            if (Objects.isNull(user.getNickname()) && Objects.nonNull(user.getMobile())) {
                String mobile = user.getMobile();
                if (mobile.length() >= 4) {
                    user.setNickname("u" + mobile.substring(mobile.length() - 4));
                }
            }

            // 设置默认头像
            if (Objects.isNull(user.getAvatar())) {
                user.setAvatar(ConstantValues.DEFAULT_AVATAR);
            }

            user.setStatus(DataStatusEnum.NORMAL.getCode());
            user.setType(UserTypeEnum.CUSTOMER.getCode());
            if (this.userWriteService.createUser(user)) {
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
                            .filter(user -> !user.getType().equals(UserTypeEnum.ADMIN.getCode()))
                            .map(this.userConverter::model2ThinResponse)
                            .collect(Collectors.toList())
            );
        });
    }

    /**
     * 用户禁用启用
     *
     * @param request 请求参数
     * @return 是否操作成功
     */
    @Override
    public Response<Boolean> updateStatus(UserStatusUpdateRequest request) {
        return execute(request, param -> {
            // TODO: 2019/3/21 query before update
            User toUpdate = this.userConverter.request2model(request);
            DataStatusEnum.validate(toUpdate.getStatus());
            return this.userWriteService.update(toUpdate);
        });
    }

    /**
     * 短信登陆-发送验证码
     *
     * @param request 手机号
     * @return 是否成功
     */
    @Override
    public Response<Boolean> sendLoginSms(UserSendLoginSmsRequest request) {
        return execute(request, param -> {
            try {
                String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
                // TODO: 2019/4/24 set expire time
                log.info("send.login.sms.code:{}", code);
                this.redisManager.set(RedisManager.Util.getKey(ConstantValues.LOGIN_SMS_PREFIX, request.getMobile()), code);
                smsSender.sendSmsMessage(request.getMobile(), code);
                return Boolean.TRUE;
            } catch (ClientException e) {
                log.error("fail.to.send.login.sms.by:{}.cause:{}", request, Throwables.getStackTraceAsString(e));
                throw new ServiceException("sms.send.fail");
            }
        });
    }

    @Override
    public Response<Long> smsLogin(UserSmsLoginRequest request) {
        return execute(request, param -> {
            // 检查验证码
            String verifyCode = this.redisManager.get(RedisManager.Util.getKey(ConstantValues.LOGIN_SMS_PREFIX, request.getMobile()));
            if (Objects.isNull(verifyCode) || !verifyCode.equals(request.getCode())) {
                throw new ServiceException("verify.code.error");
            }
            // 查找用户
            User user = this.userReadService.findSingleByCondition(request.toMap());
            if (Objects.nonNull(user)) {
                // 用户存在
                if (user.getStatus().equals(DataStatusEnum.FREEZE.getCode())) {
                    throw new ServiceException("user.freeze");
                }
            } else {
                // 用户不存在，则创建一个
                user = new User();
                user.setMobile(request.getMobile());
                // 设置默认昵称
                String mobile = user.getMobile();
                if (mobile.length() >= 4) {
                    user.setNickname("u" + mobile.substring(mobile.length() - 4));
                }
                // 设置默认头像
                user.setAvatar(ConstantValues.DEFAULT_AVATAR);
                user.setStatus(DataStatusEnum.NORMAL.getCode());
                user.setType(UserTypeEnum.CUSTOMER.getCode());
                this.userWriteService.createUser(user);
                if (Objects.isNull(user.getId())) {
                    throw new ServiceException("create.user.fail");
                }
            }
            String uuid = MyJSON.md5(user.getId().toString());
            this.redisManager.set(RedisManager.Util.getKey(ConstantValues.UUID_PREFIX, uuid), MyJSON.toJSON(UserMetaDataConverter.model2Response(user)));
            return user.getId();
        });
    }
}
