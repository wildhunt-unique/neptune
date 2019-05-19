package com.qtu404.neptune.api.request.banner;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@ApiModel("广告图 删除")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BannerRemoveRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 4433830112389740281L;

    @ApiModelProperty(value = "广告图id", required = true)
    private Long bannerId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(bannerId, "banner.id");
    }
}
