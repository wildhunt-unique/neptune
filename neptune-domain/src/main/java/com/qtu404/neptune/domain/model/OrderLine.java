package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/20 下午4:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderLine extends BaseModel implements Serializable {
    private static final long serialVersionUID = -4747005755149806759L;

    @ApiModelProperty("外部id")
    private String outId;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("'买家id'")
    private Long buyerId;

    @ApiModelProperty("买家名")
    private String buyerName;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("店铺名")
    private String shopName;

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
}
