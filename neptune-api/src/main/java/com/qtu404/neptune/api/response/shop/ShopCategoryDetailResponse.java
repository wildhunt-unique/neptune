package com.qtu404.neptune.api.response.shop;

import com.qtu404.neptune.api.response.item.ItemThinResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/4 下午5:13
 */
@ApiModel("店铺类目详情")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopCategoryDetailResponse implements Serializable {
    private static final long serialVersionUID = -8258580745210570617L;

    @ApiModelProperty("类目id")
    private Long shopCategoryId;

    @ApiModelProperty("店铺id")
    private Long ShopId;

    @ApiModelProperty("类目名")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("logo图片地址")
    private String logo;

    @ApiModelProperty("排序索引")
    private Integer sortIndex;

    @ApiModelProperty("类目下的商品")
    private List<ItemThinResponse> itemThinResponseList;
}
