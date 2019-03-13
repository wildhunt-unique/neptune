package com.qtu404.neptune.web.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.request.user.*;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.api.response.user.UserInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午5:38
 */
@Api(value = "用户-通用接口", tags = "用户-通用接口")
@RestController
@RequestMapping("api/common/user/")
public class UserCommonController {
    @Reference
    private UserFacade userFacade;

    @ApiOperation("登录")
    @PostMapping("login")
    public Response<Boolean> login(@RequestBody UserLoginRequest request, HttpSession session) {
        Response<Long> loginResponse = this.userFacade.login(request);
        if (loginResponse.isSuccess()) {
            if (loginResponse.getResult() != null) {
                session.setAttribute(ConstantValues.SESSION_CURRENT_USER_KEY, loginResponse.getResult());
                return Response.success(Boolean.TRUE);
            } else {
                return Response.success(Boolean.FALSE);
            }
        } else {
            return Response.fail(loginResponse.getError());
        }
    }

    @ApiOperation("手机号是否已存在")
    @GetMapping("exist/mobile")
    public Response<Boolean> existMobile(ExistPhoneRequest request) {
        return this.userFacade.existPhone(request);
    }

    @ApiOperation("昵称是否已存在")
    @GetMapping("exist/nickname")
    public Response<Boolean> existNickname(ExistUsernameRequest request) {
        return this.userFacade.existUsername(request);
    }

    @ApiOperation("邮箱是否已存在")
    @GetMapping("exist/email")
    public Response<Boolean> existEmail(ExistEmailRequest request) {
        return this.userFacade.existEmail(request);
    }

    @ApiOperation("注册时，发送手机验证码")
    @PostMapping("send/register/verification/sms")
    public Response<Boolean> sendRegisterVerificationSMS(@RequestBody SendRegisterVerificationSmsRequest request, HttpSession session) {
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
        request.setCode(code);
        session.setAttribute(request.getMobile(), code);
        return this.userFacade.sendRegisterVerificationSMS(request);
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public Response<Long> register(@RequestBody UserRegistryRequest request, HttpSession session) {
        String code = (String) session.getAttribute(request.getMobile());
        if (code == null || !code.equals(request.getCode())) return Response.fail("验证码错误");
        return this.userFacade.register(request);
    }

    @ApiOperation("得到用户信息")
    @GetMapping("current/user/info")
    public Response<UserInfoResponse> getCurrentUserInfo(HttpSession session) {
        if (session.getAttribute(ConstantValues.SESSION_CURRENT_USER_KEY) != null) {
            Long currentUserId = Long.valueOf(session.getAttribute(ConstantValues.SESSION_CURRENT_USER_KEY).toString());
            FindSingleUserInfoRequest request = new FindSingleUserInfoRequest(currentUserId);
            return this.userFacade.findSingleUserInfoById(request);
        } else {
            return Response.fail("not.login");
        }
    }

    @ApiOperation("注销")
    @GetMapping("logout")
    public Response<Boolean> logout(HttpSession session) {
        if (session.getAttribute(ConstantValues.SESSION_CURRENT_USER_KEY) != null) {
            session.setAttribute(ConstantValues.SESSION_CURRENT_USER_KEY, null);
        }
        return Response.success(Boolean.TRUE);
    }

    @ApiOperation("修改信息")
    @PostMapping("modify")
    public Response<UserInfoResponse> modifyUserInfo(@RequestBody UserModifyInfoRequest request) {
        return this.userFacade.modifyUserInfo(request);
    }
}
