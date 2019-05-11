package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/11
 */
@ApiModel("店铺详情查看")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopCommonGetRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 5018869925167130833L;

    @ApiModelProperty(value = "店铺id",required = true)
    private Long shopId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(shopId,"shop.id");
    }
}
