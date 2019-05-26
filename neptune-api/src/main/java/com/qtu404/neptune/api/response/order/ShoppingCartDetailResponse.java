package com.qtu404.neptune.api.response.order;

import com.qtu404.neptune.api.response.shop.ShopThinResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@ApiModel("购物车详情")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDetailResponse implements Serializable {
    private static final long serialVersionUID = 6711132597297886276L;

    @ApiModelProperty("店铺信息")
    private ShopThinResponse shopThinResponse;

    @ApiModelProperty("商品信息")
    private List<ShoppingCartThinResponse> shoppingCartLine;
}
