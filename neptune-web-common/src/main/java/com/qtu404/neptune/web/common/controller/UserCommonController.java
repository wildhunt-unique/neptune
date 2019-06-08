package com.qtu404.neptune.web.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.api.request.user.*;
import com.qtu404.neptune.api.response.user.UserThinResponse;
import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.util.model.MyJSON;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.exception.RestException;
import com.qtu404.neptune.web.common.aop.Acl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static com.qtu404.neptune.util.model.AssertUtil.assertResponse;
import static com.qtu404.neptune.web.common.util.RequestContext.getUserId;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午5:38
 */
@Api(value = "用户-通用接口", tags = "用户-通用接口")
@RestController
@RequestMapping("api/common/user/")
public class UserCommonController {
    @Value("${qtu404.host}")
    private  String HOST = "localhost";

    @Reference
    private UserFacade userFacade;

    @ApiOperation("登录")
    @PostMapping("login")
    public Response<Boolean> login(@RequestBody UserLoginRequest request, HttpServletResponse response) {
        Response<String> loginResponse = this.userFacade.login(request);
        if (loginResponse.isSuccess()) {
            this.setLoginCookie(loginResponse.getResult(),response);
            return assertResponse(Response.success(Objects.nonNull(loginResponse.getResult())));
        } else {
            throw new RestException(loginResponse.getError());
        }
    }

    @ApiOperation("短信登录-发送短信验证码")
    @PostMapping("login/sms/send")
    public Response<Boolean> smsLoginSend(@RequestBody UserSendLoginSmsRequest request) {
        return assertResponse(this.userFacade.sendLoginSms(request));
    }

    @ApiOperation("短信登录-登录验证")
    @PostMapping("login/sms/verify")
    public Response<Boolean> smsLoginVerify(@RequestBody UserSmsLoginRequest request,HttpServletResponse response) {
        Response<Long> loginResponse = this.userFacade.smsLogin(request);
        if (loginResponse.isSuccess()) {
            String tokenValue = MyJSON.md5(loginResponse.getResult().toString());
            this.setLoginCookie(tokenValue,response);
            return assertResponse(Response.success(Objects.nonNull(loginResponse.getResult())));
        } else {
            throw new RestException(loginResponse.getError());
        }
    }

    @ApiOperation("手机号是否已存在")
    @GetMapping("exist/mobile")
    public Response<Boolean> existMobile(ExistPhoneRequest request) {
        return assertResponse(this.userFacade.existPhone(request));
    }

    @ApiOperation("昵称是否已存在")
    @GetMapping("exist/nickname")
    public Response<Boolean> existNickname(ExistUsernameRequest request) {
        return assertResponse(this.userFacade.existUsername(request));
    }

    @ApiOperation("邮箱是否已存在")
    @GetMapping("exist/email")
    public Response<Boolean> existEmail(ExistEmailRequest request) {
        return assertResponse(this.userFacade.existEmail(request));
    }

    @ApiOperation("注册时，发送手机验证码")
    @PostMapping("send/register/verification/sms")
    public Response<Boolean> sendRegisterVerificationSMS(@RequestBody UserSendRegisterSmsRequest request) {
        return assertResponse(this.userFacade.sendRegisterSms(request));
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public Response<Long> register(@RequestBody UserRegistryRequest request) {
        return assertResponse(this.userFacade.register(request));
    }

    @ApiOperation("得到用户信息")
    @GetMapping("current/user/info")
    @Acl(level= AccessLevel.USER)
    public Response<UserThinResponse> getCurrentUserInfo(FindSingleUserRequest request) {
        request.setUserId(getUserId());
        return assertResponse(this.userFacade.findSingleUserInfoById(request));
    }

    @ApiOperation("注销")
    @GetMapping("logout")
    public Response<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        this.userFacade.logout(UserLogoutRequest.builder().userId(getUserId()).build());
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName() != null && cookie.getName().equals(ConstantValues.UUID_PREFIX)) {
                    cookie.setValue(null);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        return Response.success(Boolean.TRUE);
    }

    @ApiOperation("修改信息")
    @PostMapping("modify")
    @Acl(level= AccessLevel.USER)
    public Response<UserThinResponse> modifyUserInfo(@RequestBody UserModifyRequest request) {
        return assertResponse(this.userFacade.modifyUserInfo(request));
    }

    private void setLoginCookie(String tokenValue, HttpServletResponse response){
        Cookie localhostToken = new Cookie(ConstantValues.UUID_PREFIX, tokenValue);
        localhostToken.setPath("/");
        localhostToken.setMaxAge(60 * 60 * 24 * 180);

        Cookie token = new Cookie(ConstantValues.UUID_PREFIX, tokenValue);
        token.setPath("/");
        token.setDomain(HOST);
        token.setMaxAge(60 * 60 * 24 * 180);

        response.addCookie(localhostToken);
        response.addCookie(token);
    }
}
