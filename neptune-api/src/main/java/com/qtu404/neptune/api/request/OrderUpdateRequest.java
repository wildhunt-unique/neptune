package com.qtu404.neptune.api.request;

import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.common.enums.SwitchStatusEnum;
import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/21 上午9:29
 */
@ApiModel("订单级更新")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderUpdateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -6926321553258852274L;

    @ApiModelProperty(value = "订单id", required = true)
    private Long orderId;

    @ApiModelProperty("支付状态 1:已支付")
    private Integer payStatus;

    @ApiModelProperty("是否接单 1:接单 -1:拒绝")
    private Integer enableStatus;

    @ApiModelProperty("卖家留言")
    private String shopNotes;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(orderId, "order.id");
        if (Objects.nonNull(payStatus)) {
            SwitchStatusEnum.validate(payStatus);
        }
        if (Objects.nonNull(enableStatus)) {
            SwitchStatusEnum.validate(enableStatus);
        }
        if (Objects.nonNull(shopNotes) && shopNotes.length() > ConstantValues.MAX_NOTE_LENGTH) {
            throw new IllegalArgumentException("note.too.long");
        }
    }
}
