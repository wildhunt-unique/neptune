package com.qtu404.neptune.api.request.tag;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/15
 */
@ApiModel("请求参数")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagDeleteRequest  extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -4530118251116706187L;

    @ApiModelProperty(value = "标签id",required = true)
    private Long tagId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(tagId,"tag.id");
    }
}
