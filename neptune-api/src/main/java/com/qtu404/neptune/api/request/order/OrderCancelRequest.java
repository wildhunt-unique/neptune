package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/6/9
 */
@ApiModel("取消 订单")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderCancelRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 218899588557249433L;

    @ApiModelProperty(value = "用户id",hidden = true)
    private Long userId;

    @ApiModelProperty(value = "订单id",required = true)
    private Long orderId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(userId,"user.id");
        ParamUtil.nonNull(orderId,"order.id");
    }
}
