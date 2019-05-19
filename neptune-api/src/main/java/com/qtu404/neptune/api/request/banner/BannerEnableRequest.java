package com.qtu404.neptune.api.request.banner;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@ApiModel("启用/禁用 广告图")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BannerEnableRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 9001417722807566580L;

    @ApiModelProperty(value = "广告图id", required = true)
    private Long bannerId;

    @ApiModelProperty(value = "1:启用  -2:禁用", required = true)
    private Integer status;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(bannerId, "banner.id");
        ParamUtil.nonNull(status, "status");
    }
}
