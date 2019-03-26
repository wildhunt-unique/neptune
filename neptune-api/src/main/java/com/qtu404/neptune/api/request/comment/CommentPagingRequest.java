package com.qtu404.neptune.api.request.comment;

import com.qtu404.neptune.util.model.AbstractPagingRequest;
import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 下午4:32
 */
@ApiModel("请求参数")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentPagingRequest extends AbstractPagingRequest implements Serializable {
    private static final long serialVersionUID = -3484551968509283313L;

    @ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("订单id")
    private Long orderId;

    @Override
    public void checkParam() {
        ParamUtil.nonNull(shopId, "shop.id");
        super.checkParam();
    }
}
