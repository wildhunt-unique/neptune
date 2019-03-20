package com.qtu404.neptune.api.request.order;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午6:57
 */
@ApiModel("订单创建")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -759568499080104245L;

    @ApiModelProperty("外部编码")
    private String outId;

    @ApiModelProperty(value = "买家id", hidden = true)
    private Long buyerId;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("买家留言")
    private String buyerNotes;

    @ApiModelProperty("卖家留言")
    private String shopNotes;

    @ApiModelProperty("订单中的商品信息")
    private List<ItemOrderLineCreateRequest> orderLine;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(buyerId, "buyer.id");
        ParamUtil.nonNull(shopId, "shop.id");
        ParamUtil.nonEmpty(orderLine,"item.list");
    }
}

