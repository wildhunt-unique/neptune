package com.qtu404.neptune.api.response.item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/4 下午5:18
 */
@ApiModel("商品简略信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemThinResponse implements Serializable {
    private static final long serialVersionUID = 7844264381856302260L;

    @ApiModelProperty("商品id")
    private Long itemId;

    @ApiModelProperty("类目id")
    private Long categoryId;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("商品名")
    private String name;

    @ApiModelProperty("商品广告")
    private String advertise;

    @ApiModelProperty("主图")
    private String mainImage;

    @ApiModelProperty("商品属性")
    private Map<String,Object> attribute;

    @ApiModelProperty("额外信息")
    private Map<String, Object> extra;

    @ApiModelProperty("价格")
    private Double price;

    @ApiModelProperty("库存")
    private Long inventory;

    @ApiModelProperty("状态 1:正常 -1:售罄")
    private Integer status;
}
