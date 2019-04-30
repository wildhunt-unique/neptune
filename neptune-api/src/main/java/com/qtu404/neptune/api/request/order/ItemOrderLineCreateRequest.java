package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/20 下午5:34
 */
@ApiModel("商品级订单")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemOrderLineCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 7358151520437830258L;

    @ApiModelProperty("商品id")
    private Long itemId;

    @ApiModelProperty("购买数量")
    private Integer quantity;

    @ApiModelProperty("实际支付金额")
    private Integer paidAmount;

    @ApiModelProperty("商品销售属性")
    private Map<String, Object> itemAttribute;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(itemId, "item.id");
        ParamUtil.nonNull(paidAmount, "paid.amount");
        if (paidAmount < 0) {
            paidAmount = 0;
        }
        if (Objects.isNull(quantity) || quantity <= 0) {
            quantity = 1;
        }
    }
}
