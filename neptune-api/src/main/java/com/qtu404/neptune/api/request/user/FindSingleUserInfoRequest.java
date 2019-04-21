package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@ApiModel("用户信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FindSingleUserInfoRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 9213294151679558866L;
    @ApiModelProperty(value = "用户id",hidden = true)
    private Long userId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(userId,"user.id");
    }
}
