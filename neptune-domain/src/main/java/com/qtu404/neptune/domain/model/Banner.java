package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Banner extends BaseModel implements Serializable {
    private static final long serialVersionUID = 5169320000039315736L;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片地址")
    private String imageUrl;
}
