package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@ApiModel("购物车更新参数")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartCreateOrUpdateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -5627148182974094918L;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "商品id", required = true)
    private Long itemId;

    @ApiModelProperty(value = "数量，小于等于0就是删除", required = true)
    private Integer quantity;

    @ApiModelProperty("商品销售属性,只是更新可以不传")
    private Map<String, Object> itemAttribute;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(userId, "user.id");
        ParamUtil.nonNull(itemId, "item.id");
        ParamUtil.nonNull(quantity, "quantity");
    }
}
