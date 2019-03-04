package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:48
 */
@ApiModel("创建店铺类目")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopCategoryCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -1251480275325070604L;

    @ApiModelProperty(value = "店铺id", required = true)
    private Long ShopId;

    @ApiModelProperty(value = "类目名", required = true)
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("父节点")
    private Long pid;

    @ApiModelProperty("logo")
    private String logo;

    @ApiModelProperty("排序索引")
    private Integer sortIndex;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(ShopId, "shop.id");
        ParamUtil.nonNull(name, "category.name");
    }
}
