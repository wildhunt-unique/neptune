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
@ApiModel("修改信息请求参数")
public class UserModifyInfoRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -5500255935613531806L;
    @ApiModelProperty("要改哪个用户，不可null")
    private Long id;
    @ApiModelProperty("新名字，可null")
    private String name;
    @ApiModelProperty("新头像，可null")
    private String avatar;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.id, "id.not.be.null");
    }
}
