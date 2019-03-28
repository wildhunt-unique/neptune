package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/21 下午7:08
 */
@ApiModel("订单详情")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderDetailRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -8733498265388485550L;

    @ApiModelProperty(value = "订单id", required = true)
    private Long orderId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(orderId, "order.id");
    }
}
