package com.qtu404.neptune.api.response.shop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/6 下午2:41
 */
@ApiModel("店铺信息")
@Data
public class ShopThinResponse implements Serializable {
    private static final long serialVersionUID = -1865970656120413433L;

    @ApiModelProperty("店铺Id")
    private String shopId;

    @ApiModelProperty("卖家id")
    private Long userId;

    @ApiModelProperty("卖家名")
    private String userName;

    @ApiModelProperty("店名")
    private String name;

    @ApiModelProperty("联系电话")
    private String mobile;

    @ApiModelProperty("店铺图片url")
    private String imageUrl;

    @ApiModelProperty("店铺地址")
    private String address;

    @ApiModelProperty("店铺状态 1:营业 -1:歇业 -2:冻结")
    private Integer status;
}
