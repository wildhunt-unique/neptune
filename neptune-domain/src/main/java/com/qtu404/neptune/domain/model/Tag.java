package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:38
 */
@ApiModel("标签")
@Data
@EqualsAndHashCode(callSuper = true)
public class Tag extends BaseModel implements Serializable {
    private static final long serialVersionUID = -8717475597076536248L;

    /**
     * 标签名
     */
    @ApiModelProperty("标签名")
    private String name;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 内容
     */
    @ApiModelProperty("标签内容")
    private String content;
}
