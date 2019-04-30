package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/13 上午9:37
 */
@ApiModel("店铺类目更新请求")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopCategoryUpdateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -4672294135826283760L;

    @ApiModelProperty(value = "店铺类目id", required = true)
    private Long shopCategoryId;

    @ApiModelProperty(value = "类目名")
    private String name;

    @ApiModelProperty("排序索引")
    private Integer sortIndex;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态 1:正常 ")
    private Integer status;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(shopCategoryId,"shop.category.id");
    }
}
