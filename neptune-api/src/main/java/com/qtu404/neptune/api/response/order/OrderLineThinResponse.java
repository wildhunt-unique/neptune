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
@ApiModel("订单行")
public class OrderLineThinResponse implements Serializable {
    private static final long serialVersionUID = 3459286715589469697L;

    @ApiModelProperty("订单行id")
    private Long orderLineId;

    @ApiModelProperty("商品id")
    private Long itemId;

    @ApiModelProperty("商品code")
    private String itemCode;

    @ApiModelProperty("商品名")
    private String itemName;

    @ApiModelProperty("商品主图")
    private String itemImage;

    @ApiModelProperty("商品销售属性")
    private String itemAttr;

    @ApiModelProperty("购买数量")
    private Integer quantity;

    @ApiModelProperty("实际支付金额")
    private Integer paidAmount;

    @ApiModelProperty("确认时间")
    private Date confirmAt;

    @ApiModelProperty("收货状态")
    private Integer receiveStatus;

    @ApiModelProperty("订单创建时间")
    private Date createdAt;
}
