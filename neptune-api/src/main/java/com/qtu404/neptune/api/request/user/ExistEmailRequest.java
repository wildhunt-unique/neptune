package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@ApiModel("邮箱查重")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExistEmailRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 2265401641823574985L;
    @ApiModelProperty(value = "要判断的邮箱地址",required = true)
    private String email;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.email, "email");
    }
}
