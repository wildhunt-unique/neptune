package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractPagingRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/6 下午2:35
 */
@ApiModel("店铺分页请求参数")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopPagingRequest extends AbstractPagingRequest implements Serializable {
    private static final long serialVersionUID = -3007249300428693632L;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("店铺名")
    private String name;

    @ApiModelProperty("卖家名")
    private String userName;

    @ApiModelProperty("卖家id")
    private Long userId;

    @ApiModelProperty("联系电话")
    private String mobile;

    @ApiModelProperty("店铺状态 1:营业 -1:歇业 -2:冻结")
    private Integer status;

    @ApiModelProperty("标签id")
    private Long tagId;


    @Override
    public void checkParam() {
        super.checkParam();
    }
}
