package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/4 下午5:03
 */
@ApiModel("店铺详情查看")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopDetailRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -6228026655168295797L;

    @ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.shopId, "shop.id");
    }
}
