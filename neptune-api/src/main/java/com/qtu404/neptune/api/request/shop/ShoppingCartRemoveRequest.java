package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/6/9
 */
@ApiModel("购物车删除")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartRemoveRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -8892568395902836105L;

    @ApiModelProperty(value = "购物车行id", required = true)
    private Long shoppingCartId;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long operatorId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(shoppingCartId, "shopping.card.id");
        ParamUtil.nonNull(operatorId, "operator.id");
    }
}
