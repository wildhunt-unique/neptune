package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@ApiModel("点前登陆店铺信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopGetRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 1163655745535898002L;

    @ApiModelProperty(value = "店铺id",hidden = true)
    private Long shopId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(shopId,"shop.id");
    }
}
