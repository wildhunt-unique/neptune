package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel("手机号查重")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExistPhoneRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -1176704228274943634L;
    @ApiModelProperty("要判断的手机号")
    private String mobile;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.mobile,"mobile");
    }
}
