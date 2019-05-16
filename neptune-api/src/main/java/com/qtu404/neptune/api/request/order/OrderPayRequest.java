package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/15
 */
@ApiModel("支付订单")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderPayRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 5054590847050286410L;

    @ApiModelProperty(value = "订单id",required = true)
    private Long orderId;

    @ApiModelProperty(value = "买家id",hidden = true)
    private Long buyerId;

    @ApiModelProperty(value = "支付金额",required = true)
    private Integer paidAmount;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(orderId,"order.id");
        ParamUtil.nonNull(buyerId,"buyer.id");
        ParamUtil.nonNull(paidAmount,"paid,amount");
    }
}
