package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Complaint extends BaseModel implements Serializable {
    private static final long serialVersionUID = -7132475922286196905L;

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

    @ApiModelProperty("额外信息")
    private Map<String, Object> extra;

    @ApiModelProperty("创建时间")
    private Date createdAt;
}
