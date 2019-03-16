package com.qtu404.neptune.api.request;

import com.qtu404.neptune.util.model.AbstractRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午2:17
 */
 @ApiModel("检查参数")
 @Data
 @Builder
 @NoArgsConstructor
 @AllArgsConstructor
 @EqualsAndHashCode(callSuper = true)
public class DubboCheckRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -2654584783355545802L;

    @ApiModelProperty("姓名")
    private String name;

    @Override
    public void checkParam() {
        super.checkParam();
        if (Objects.isNull(name)) name = "stranger";
    }
}
