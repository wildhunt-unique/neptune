package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractPagingRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@ApiModel("订单分页")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderPagingRequest extends AbstractPagingRequest implements Serializable {
    private static final long serialVersionUID = 1792871564252216081L;

    @ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;

    @ApiModelProperty("是否接单")
    private Integer enableStatus;

    @ApiModelProperty("支付状态")
    private Integer payStatus;

    @ApiModelProperty("收货状态")
    private Integer receiveStatus;

    @ApiModelProperty("退款状态状态")
    private Integer reverseStatus;

    @ApiModelProperty("买家id")
    private Long buyerId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonExist(shopId, "shop.id");
    }
}
