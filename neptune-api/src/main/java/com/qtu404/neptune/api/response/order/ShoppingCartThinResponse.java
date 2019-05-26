package com.qtu404.neptune.api.response.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@ApiModel("购物车商品信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartThinResponse implements Serializable {
    private static final long serialVersionUID = -440321151372615270L;

    @ApiModelProperty("id")
    private Long shoppingCartId;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("商品id")
    private Long itemId;

    @ApiModelProperty("商品名")
    private String itemName;

    @ApiModelProperty("商品图")
    private String itemImage;

    @ApiModelProperty("数量")
    private Integer quantity;

    @ApiModelProperty("单价")
    private Integer price;

    @ApiModelProperty("商品销售属性")
    private Map<String, Object> itemAttribute;
}
