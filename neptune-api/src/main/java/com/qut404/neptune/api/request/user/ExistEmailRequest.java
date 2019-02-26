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
public class ExistEmailRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 2265401641823574985L;
    @ApiModelProperty("要判断的邮箱地址")
    private String email;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.email, "email");
    }
}
