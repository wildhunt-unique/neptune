package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@ApiModel("购物车详情")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartDetailRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 7993200997975669968L;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(userId, "user.id");
    }
}
