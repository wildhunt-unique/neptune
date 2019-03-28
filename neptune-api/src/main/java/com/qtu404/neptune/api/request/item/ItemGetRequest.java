package com.qtu404.neptune.api.request.item;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/28 上午10:34
 */
@ApiModel("查看商品信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ItemGetRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 6540459370524329045L;

    @ApiModelProperty(value = "商品id", required = true)
    private Long itemId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(itemId, "item.id");
    }
}
