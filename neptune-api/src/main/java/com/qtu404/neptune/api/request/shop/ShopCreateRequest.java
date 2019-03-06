package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:34
 */
@ApiModel("请求参数")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -5179881697726592981L;

    @ApiModelProperty(value = "卖家id", required = true)
    private Long userId;

    @ApiModelProperty(hidden = true)
    private String userName;

    @ApiModelProperty(value = "店铺名", required = true)
    private String name;

    @ApiModelProperty(hidden = true)
    private Integer type;

    @ApiModelProperty("店铺联系电话")
    private Integer mobile;

    @ApiModelProperty("店铺联系邮箱")
    private String email;

    @ApiModelProperty("店铺图片url")
    private String imageUrl;

    @ApiModelProperty("店铺地址")
    private String address;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(name, "shop.name");
        ParamUtil.nonNull(userId, "user.id");
    }
}
