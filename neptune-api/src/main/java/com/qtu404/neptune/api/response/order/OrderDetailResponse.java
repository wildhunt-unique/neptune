package com.qtu404.neptune.api.response.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/21 下午7:10
 */
@Data
@ApiModel("订单详情")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse implements Serializable {
    private static final long serialVersionUID = -3205884515905994180L;

    @ApiModelProperty("订单级信息")
    private OrderThinResponse orderThinResponse;

    @ApiModelProperty("订单行信息")
    private List<OrderLineThinResponse> orderLineThinResponseList;
}
