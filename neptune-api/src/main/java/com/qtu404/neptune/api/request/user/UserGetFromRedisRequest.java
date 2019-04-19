package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/18
 */
@ApiModel("读取redis参数")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserGetFromRedisRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -5927232603035387246L;

    private String key;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(key,"key");
    }
}
