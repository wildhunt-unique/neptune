package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("支付单")
public class Payment extends BaseModel implements Serializable {
    private static final long serialVersionUID = 5287788255891385072L;

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
}
