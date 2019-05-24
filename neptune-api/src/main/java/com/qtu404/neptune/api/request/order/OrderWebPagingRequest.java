package com.qtu404.neptune.api.request.order;


import com.qtu404.neptune.util.model.AbstractPagingRequest;
import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/24
 */
@ApiModel("订单查看-web")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderWebPagingRequest extends AbstractPagingRequest implements Serializable {
    private static final long serialVersionUID = -7759863479733471085L;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long buyerId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(buyerId, "buyer.id");
    }
}
