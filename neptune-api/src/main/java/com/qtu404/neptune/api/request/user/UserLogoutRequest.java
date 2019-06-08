package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/6/7
 */
@ApiModel("注销请求")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserLogoutRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 8390097532392781547L;
    private Long userId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(userId,"user.id");
    }
}
