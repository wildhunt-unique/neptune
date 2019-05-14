package com.qtu404.neptune.api.request.item;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午9:25
 */
@ApiModel("商品修改")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemUpdateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 3845166397020016751L;

    @ApiModelProperty(value = "商品id", required = true)
    private Long itemId;

    @ApiModelProperty(value = "类目id")
    private Long categoryId;

    @ApiModelProperty("商品名")
    private String name;

    @ApiModelProperty("商品广告")
    private String advertise;

    @ApiModelProperty("主图")
    private String mainImage;

    @ApiModelProperty("视频链接")
    private String videoUrl;

    @ApiModelProperty("商品属性")
    private Map<String, Object> attribute;

    @ApiModelProperty("额外信息")
    private Map<String,Object> extra;

    @ApiModelProperty("价格")
    private Double price;

    @ApiModelProperty("1:上架，-2:下架")
    private Integer status;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.itemId, "item.id");
        if (Objects.nonNull(this.price)) {
            if (price < 0) {
                throw new IllegalArgumentException("price.not.be.less.than.zero");
            }
        }
        if (Objects.nonNull(status)) {
            if (status != -2 && status != 1) {
                throw new IllegalArgumentException("illegal.status");
            }
        }
    }
}
