package com.qtu404.neptune.api.response.shop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/16 下午6:00
 */
@ApiModel("店铺类目list")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopCategoryListResponse implements Serializable {
    private static final long serialVersionUID = -5519988634873359039L;

    @ApiModelProperty("店铺类目list")
    private List<ShopCategoryDetailResponse> categoryList;
}
