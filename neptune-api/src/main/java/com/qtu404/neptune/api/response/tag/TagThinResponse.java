package com.qtu404.neptune.api.response.tag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午2:08
 */
@ApiModel("标签简略信息")
@Data
public class TagThinResponse implements Serializable {
    private static final long serialVersionUID = 7333949739543739712L;

    /**
     * 标签id
     */
    @ApiModelProperty("标签id")
    private Long tagId;

    /**
     * 标签名
     */
    @ApiModelProperty("标签名")
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty("标签状态")
    private Integer status;

    /**
     * 内容
     */
    @ApiModelProperty("标签内容")
    private String content;
}
