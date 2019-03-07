package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午2:53
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("标签绑定")
@Data
public class TagBinding extends BaseModel implements Serializable {
    private static final long serialVersionUID = -2929123622089207280L;

    /**
     * 标签id
     */
    @ApiModelProperty("标签id")
    private Long tagId;

    /**
     * 标签id
     */
    @ApiModelProperty("绑定对象id")
    private Long targetId;

    /**
     * 标签id
     */
    @ApiModelProperty("绑定对象类型")
    private Integer type;
}
