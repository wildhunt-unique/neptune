package com.qtu404.neptune.api.response.complaint;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@Data
public class ComplaintThinResponse implements Serializable {
    private static final long serialVersionUID = -1636398931974587059L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户手机号")
    private String userMobile;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("店铺名")
    private String shopName;

    @ApiModelProperty("店铺联系方式")
    private String shopMobile;

    @ApiModelProperty("创建时间")
    private Date createdAt;
}
