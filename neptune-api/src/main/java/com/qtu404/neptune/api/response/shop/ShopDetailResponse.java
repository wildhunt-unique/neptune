package com.qtu404.neptune.api.response.shop;

import com.qtu404.neptune.api.response.tag.TagThinResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/4 下午5:04
 */
@ApiModel("店铺详情")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDetailResponse implements Serializable {
    private static final long serialVersionUID = 3794792488941197469L;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("卖家id")
    private Long userId;

    @ApiModelProperty("卖家名")
    private String userName;

    @ApiModelProperty("店名")
    private String name;

    @ApiModelProperty("联系电话")
    private String mobile;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty("店铺图片url")
    private String imageUrl;

    @ApiModelProperty("店铺地址")
    private String address;

    @ApiModelProperty("店铺标签")
    private List<TagThinResponse> tagList;

    @ApiModelProperty("店铺类目列表")
    private List<ShopCategoryDetailResponse> shopCategoryDetailResponseList;
}
