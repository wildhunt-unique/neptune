package com.qtu404.neptune.api.response.shop;

import com.qtu404.neptune.api.response.item.ItemThinResponse;
import com.qtu404.neptune.util.model.AbstractPagingRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/24
 */
@ApiModel("店铺以及搜索的商品信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopWithSearchItemResponse implements Serializable {
    private static final long serialVersionUID = -2545584495627659955L;

    @ApiModelProperty("店铺信息")
    private ShopThinResponse shopInfo;

    @ApiModelProperty("商品信息")
    private List<ItemThinResponse> itemList;
}
