package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@ApiModel("注册")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRegistryRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -493769781089893823L;
    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty("用户类型")
    private Integer type;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "注册验证码", required = true)
    private String code;

    @Override
    public void checkParam() {
        // TODO: 2019/2/27 检查手机号码的格式
        super.checkParam();
        ParamUtil.nonNull(this.mobile, "mobile");
        ParamUtil.nonNull(this.password, "password");
    }

}
