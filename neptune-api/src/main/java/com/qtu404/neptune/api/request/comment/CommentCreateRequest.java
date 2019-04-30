package com.qtu404.neptune.api.request.comment;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:59
 */
@ApiModel("创建评价")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 6605219474814443757L;

    @ApiModelProperty(value = "订单id", required = true)
    private Long orderId;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "评分", required = true)
    private Integer rate;

    @ApiModelProperty(value = "评价内容", required = true)
    private String context;

    @ApiModelProperty("评价图片")
    private Map<String, Object> images;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(orderId, "order");
        ParamUtil.nonNull(userId, "user.id");
        ParamUtil.nonNull(rate, "rate");
        ParamUtil.nonNull(context, "context");
    }
}
