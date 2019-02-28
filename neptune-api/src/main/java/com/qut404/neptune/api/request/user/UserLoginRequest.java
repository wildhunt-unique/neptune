package com.qut404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@ApiModel("登录")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserLoginRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 6488434159625540269L;
    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户类型")
    private Integer type;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.mobile,"mobile");
        ParamUtil.nonNull(this.password,"password");
    }
}
