package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractPagingRequest;
import com.qtu404.neptune.util.model.AbstractRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/15
 */
@ApiModel("商家支付单分页")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PaymentPagingRequest extends AbstractPagingRequest implements Serializable {
    private static final long serialVersionUID = 1756007824247795525L;

    @ApiModelProperty(value = "店铺id",hidden = true)
    private Long shopId;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("'买家手机号'")
    private String buyerMobile;

    @Override
    public void checkParam() {
        super.checkParam();
    }
}
