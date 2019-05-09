package com.qtu404.neptune.api.request.complaint;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@ApiModel("创建投诉")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ComplaintCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -3276102576981055519L;

    @ApiModelProperty(value = "用户id",hidden = true)
    private Long userId;

    @ApiModelProperty(value = "店铺id",required = true)
    private Long shopId;

    @ApiModelProperty(value = "标题",required = true)
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("额外信息")
    private Map<String, Object> extra;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(shopId,"shop.id");
        ParamUtil.nonNull(title,"title");
        ParamUtil.nonNull(userId,"user.id");
    }
}
