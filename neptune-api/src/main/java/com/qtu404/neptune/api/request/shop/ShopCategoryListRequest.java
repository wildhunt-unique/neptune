package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/7
 */
@ApiModel("店铺类目查询")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopCategoryListRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -1471795549267618679L;

    @ApiModelProperty(value = "店铺id", hidden = true)
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
