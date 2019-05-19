package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractPagingRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/16
 */
@ApiModel("支付单分页-平台")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentPagingAdminRequest extends AbstractPagingRequest implements Serializable {
    private static final long serialVersionUID = 3525856424206822437L;
    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("'买家手机号'")
    private String buyerMobile;

    @ApiModelProperty("'买家id'")
    private Long buyerId;

    @Override
    public void checkParam() {
        super.checkParam();
    }
}
