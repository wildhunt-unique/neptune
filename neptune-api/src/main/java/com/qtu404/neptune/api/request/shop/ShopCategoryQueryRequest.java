package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/16 下午6:02
 */
@ApiModel("店铺类目查询")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopCategoryQueryRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -4873271571997524756L;

    @ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;

    @ApiModelProperty("类目id")
    private Long categoryId;

    @ApiModelProperty("是否带商品信息")
    private Boolean withItemInfo;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(shopId, "shop.id");
        if (Objects.isNull(withItemInfo)) {
            withItemInfo = Boolean.FALSE;
        }
    }
}
