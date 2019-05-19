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
@ApiModel("广告图创建")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BannerCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 2556275596499901442L;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(type,"type");
        ParamUtil.nonNull(name,"name");
        ParamUtil.nonNull(imageUrl,"imageUrl");
    }
}
