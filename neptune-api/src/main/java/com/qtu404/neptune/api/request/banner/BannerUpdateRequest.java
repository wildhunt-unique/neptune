package com.qtu404.neptune.api.request.banner;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@ApiModel("更新广告图")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BannerUpdateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 6565197968463993284L;

    @ApiModelProperty(value = "id", required = true)
    private Long bannerId;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("1:启用 -2:禁用")
    private Integer status;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(bannerId, "banner.id");
    }
}
