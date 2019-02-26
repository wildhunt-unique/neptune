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
public class SendRegisterVerificationSmsRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 4990599671156812384L;
    @ApiModelProperty("发到哪个手机号")
    private String mobile;
    @ApiModelProperty("前端不填！！")
    private String code;

    @Override
    public void checkParam() {
        ParamUtil.nonNull(this.mobile, "mobile.not.be.null");
        ParamUtil.nonNull(this.code, "code.not.be.null");
    }
}
