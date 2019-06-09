package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@ApiModel("购物车全量更新")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShoppingFullUpdateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -2386268404902434906L;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "购物车列表")
    private List<ShoppingCartCreateRequest> shoppingCartList;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(userId, "user.id");
        if (!CollectionUtils.isEmpty(shoppingCartList)) {
            shoppingCartList.forEach(requestLine -> {
                requestLine.setUserId(userId);
                requestLine.checkParam();
            });
        }
    }
}
