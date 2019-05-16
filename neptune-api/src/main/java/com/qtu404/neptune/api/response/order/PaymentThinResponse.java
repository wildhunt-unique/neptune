package com.qtu404.neptune.api.response.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/15
 */
@ApiModel("支付单")
@Data
public class PaymentThinResponse implements Serializable {
    private static final long serialVersionUID = 5737435228250539989L;

    @ApiModelProperty("支付单号")
    private Long paymentId;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("外部id")
    private String outId;

    @ApiModelProperty("买家id")
    private Long buyerId;

    @ApiModelProperty("买家名")
    private String buyerName;

    @ApiModelProperty("'买家手机号'")
    private String buyerMobile;

    @ApiModelProperty("'买家手机号'")
    private Long shopId;

    @ApiModelProperty("店铺名")
    private String shopName;

    @ApiModelProperty("'支付金额'")
    private Integer paidAmount;

    @ApiModelProperty("'商品总数'")
    private Integer itemTotalAmount;

    @ApiModelProperty("支付时间")
    private Date createdAt;
}
