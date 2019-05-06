package com.qtu404.neptune.api.request.item;

import com.qtu404.neptune.util.model.AbstractPagingRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/16 下午5:26
 */
@ApiModel("商品分页请求")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemPagingRequest extends AbstractPagingRequest implements Serializable {
    private static final long serialVersionUID = 7158262317813768231L;

    /**
     * 店铺id
     */
    @ApiModelProperty(value = "店铺id", hidden = true)
    private Long shopId;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long itemId;

    /**
     * 商品id
     */
    @ApiModelProperty("商品状态")
    private Integer status;

    /**
     * 类目id
     */
    @ApiModelProperty("类目id")
    private Long categoryId;

    /**
     * 商品编码
     */
    @ApiModelProperty("商品编码")
    private String itemCode;

    /**
     * 商品名
     */
    @ApiModelProperty("商品名")
    private String name;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(shopId, "shop.id");
    }
}
