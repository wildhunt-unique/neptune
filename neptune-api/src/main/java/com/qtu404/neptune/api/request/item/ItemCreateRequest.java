package com.qtu404.neptune.api.request.item;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:36
 */
@ApiModel("创建商品参数")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -8256216416839307957L;

    @ApiModelProperty(value = "类目id", required = true)
    private Long categoryId;

    @ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;

    @ApiModelProperty(value = "商品名", required = true)
    private String name;

    @ApiModelProperty(value = "价格", required = true)
    private Double price;

    @ApiModelProperty(value = "主图", required = true)
    private String mainImage;

    @ApiModelProperty("商品编码")
    private String itemCode;

    @ApiModelProperty("商品广告")
    private String advertise;

    @ApiModelProperty("视频链接")
    private String videoUrl;

    @ApiModelProperty("商品属性")
    private Map<String, Object> attribute;

    @ApiModelProperty("额外信息")
    private Map<String, Object> extra;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.name, "item.name");
        ParamUtil.nonNull(this.shopId, "shop.id");
        ParamUtil.nonNull(this.price, "price");
        ParamUtil.nonNull(this.mainImage, "image");
        ParamUtil.nonNull(this.categoryId, "category.id");
        if (price < 0) {
            throw new IllegalArgumentException("price.not.be.less.than.zero");
        }
    }
}
