package com.qtu404.neptune.api.request.complaint;

import com.qtu404.neptune.util.model.AbstractPagingRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@ApiModel("投诉分页")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ComplaintPagingRequest extends AbstractPagingRequest implements Serializable {
    private static final long serialVersionUID = 8733555358430439905L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("投诉人手机号")
    private String userMobile;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @Override
    public void checkParam() {
        super.checkParam();
    }
}
