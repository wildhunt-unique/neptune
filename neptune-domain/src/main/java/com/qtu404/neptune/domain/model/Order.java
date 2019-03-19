package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午5:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Order extends BaseModel implements Serializable {
    private static final long serialVersionUID = 7826712058680293421L;

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

    @ApiModelProperty("是否可见")
    private Integer enableStatus;

    @ApiModelProperty("支付状态")
    private Integer payStatus;

    @ApiModelProperty("收货状态")
    private Integer receiveStatus;

    @ApiModelProperty("退款状态状态")
    private Integer reverseStatus;

    @ApiModelProperty("支付时间")
    private Date payAt;

    @ApiModelProperty("发货时间")
    private Date shippingAt;

    @ApiModelProperty("确认收货时间")
    private Date confirmAt;

    @ApiModelProperty("实际支付金额")
    private Long paidAmount;

    @ApiModelProperty("'订单完成时间'")
    private Date accomplishAt;

    @ApiModelProperty("商品数量")
    private Integer itemTotalAmount;

    @ApiModelProperty("收货地址")
    private String deliveryAddress;

    @ApiModelProperty("买家留言")
    private String buyerNotes;

    @ApiModelProperty("卖家留言")
    private String shopNotes;

    @ApiModelProperty("打标")
    private Integer tag;
}
