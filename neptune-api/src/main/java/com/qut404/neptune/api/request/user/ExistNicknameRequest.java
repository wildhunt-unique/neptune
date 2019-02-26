package com.qut404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("请求参数")
public class ExistNicknameRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -104477524093039668L;
    @ApiModelProperty("要判断的昵称")
    private String nickname;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.nickname,"nickname");
    }
}
