package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午11:42
 */
@ApiModel("店铺修改")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopUpdateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -5344686480504023539L;

    @ApiModelProperty(value = "店铺id", required = true)
    private Long shopId;

    @ApiModelProperty(hidden = true)
    private Long operator;

    @ApiModelProperty("卖家名")
    private String userName;

    @ApiModelProperty("店铺名")
    private String name;

    @ApiModelProperty("店铺联系电话")
    private String mobile;

    @ApiModelProperty("店铺联系邮箱")
    private String email;

    @ApiModelProperty("店铺图片url")
    private String imageUrl;

    @ApiModelProperty("店铺地址")
    private String address;

    @ApiModelProperty("店铺状态 营业:1 暂停营业:-2")
    private Integer status;

    @ApiModelProperty("店铺标签id")
    private List<Long> tagIds;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.shopId, "shop.id");
        ParamUtil.nonNull(this.operator, "user.id");
        if (Objects.nonNull(status)) {
            if (status != -2 && status != 1) {
                throw new IllegalArgumentException("illegal.status");
            }
        }
    }
}
