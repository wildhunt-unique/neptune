package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@ApiModel("发短信")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SendRegisterVerificationSmsRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 1651194469071585519L;

    @ApiModelProperty(value = "发到哪个手机号", required = true)
    private String mobile;

    @Override
    public void checkParam() {
        ParamUtil.nonNull(this.mobile, "mobile.not.be.null");
    }
}
