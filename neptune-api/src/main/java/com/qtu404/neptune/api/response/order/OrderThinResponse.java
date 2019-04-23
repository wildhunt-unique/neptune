package com.qtu404.neptune.api.response.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/21 下午7:18
 */
@Data
@ApiModel("订单级信息")
public class OrderThinResponse implements Serializable {
    private static final long serialVersionUID = -1534653513336032877L;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("外部编码")
    private String outId;

    @ApiModelProperty("买家id")
    private Long buyerId;

    @ApiModelProperty("买家姓名")
    private String buyerName;

    @ApiModelProperty("店铺名")
    private String ShopName;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("是否接单")
    private Integer enableStatus;

    @ApiModelProperty("支付状态")
    private Integer payStatus;

    @ApiModelProperty("退款状态状态")
    private Integer reverseStatus;

    @ApiModelProperty("支付时间")
    private Date payAt;

    @ApiModelProperty("实际支付金额")
    private Integer paidAmount;

    @ApiModelProperty("'订单完成时间'")
    private Date accomplishAt;

    @ApiModelProperty("商品数量")
    private Integer itemTotalAmount;

    @ApiModelProperty("买家留言")
    private String buyerNotes;

    @ApiModelProperty("卖家留言")
    private String shopNotes;

    @ApiModelProperty("创建时间")
    private Date createdAt;

    @ApiModelProperty("买家手机号")
    private String buyerMobile;
}
