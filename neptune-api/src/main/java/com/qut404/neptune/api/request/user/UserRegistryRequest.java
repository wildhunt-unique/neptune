package com.qut404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("请求参数")
public class UserRegistryRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -493769781089893823L;
    @ApiModelProperty("昵称，不可为空")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("用户类型")
    private Integer type;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("注册验证码")
    private String code;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.nickname, "nickname");
        ParamUtil.nonNull(this.mobile, "mobile");
        ParamUtil.nonNull(this.type, "type");
        ParamUtil.nonNull(this.password, "password");
    }

}
