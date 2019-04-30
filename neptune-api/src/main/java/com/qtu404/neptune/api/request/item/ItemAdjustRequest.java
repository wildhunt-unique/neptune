package com.qtu404.neptune.api.request.item;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午9:43
 */
@ApiModel("设置库存")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemAdjustRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 7844973069012623256L;

    @ApiModelProperty(value = "商品id", required = true)
    private Long itemId;

    @ApiModelProperty(value = "要设置的库存量", required = true)
    private Long inventory;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.itemId, "item.id");
        ParamUtil.nonNull(this.inventory, "item.inventory");
        if (inventory < 0) {
            throw new IllegalArgumentException("inventory.not.be.less.than.zero");
        }
    }
}