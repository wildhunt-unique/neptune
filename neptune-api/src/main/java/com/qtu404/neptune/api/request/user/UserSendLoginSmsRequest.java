package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/17 上午12:05
 */
@ApiModel("发送登录验证短信")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserSendLoginSmsRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 4891199107857827683L;

    @ApiModelProperty(value = "手机号",required = true)
    private String mobile;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(mobile,"mobile");
    }
}
