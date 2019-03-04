package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;


@ApiModel("改信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserModifyInfoRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -5500255935613531806L;
    @ApiModelProperty(value = "要改哪个用户", required = true)
    private Long id;
    @ApiModelProperty("新名字")
    private String name;
    @ApiModelProperty("新头像")
    private String avatar;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.id, "id.not.be.null");
    }
}
