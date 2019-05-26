package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@ApiModel("购物车店铺重置")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartShopRemoveAllRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -1617796630467934015L;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "店铺id", required = true)
    private List<Long> shopIdList;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonEmpty(shopIdList, "shop.id.list");
        ParamUtil.nonNull(userId, "user.id");
    }
}
