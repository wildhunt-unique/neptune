package com.qtu404.neptune.api.response.banner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@ApiModel("广告图thin")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerThinResponse implements Serializable {
    private static final long serialVersionUID = 8573729109252301377L;

    @ApiModelProperty("广告图id")
    private Long bannerId;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("1:启用 -2:禁用")
    private Integer status;
}
