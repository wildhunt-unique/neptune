package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShopCategory extends BaseModel implements Serializable {
    private static final long serialVersionUID = -4578729119369344302L;

    /**
     * 店铺id
     */
    @ApiModelProperty("店铺id")
    private Long ShopId;

    /**
     * 类目名
     */
    @ApiModelProperty("类目名")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 父节点
     */
    @ApiModelProperty("父节点")
    private Long pid;

    /**
     * logo
     */
    @ApiModelProperty("logo")
    private String logo;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    private Integer level;

    /**
     * 是否有孩子节点
     */
    @ApiModelProperty("是否有孩子节点")
    private Boolean hasChildren;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 排序索引
     */
    @ApiModelProperty("排序索引")
    private Integer sortIndex;

    /**
     * 是否展开
     */
    @ApiModelProperty("是否展开")
    private Boolean disclosed;
}
