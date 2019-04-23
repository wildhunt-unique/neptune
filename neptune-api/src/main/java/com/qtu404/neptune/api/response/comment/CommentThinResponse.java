package com.qtu404.neptune.api.response.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 下午4:18
 */
@Data
@ApiModel("评价信息")
public class CommentThinResponse implements Serializable {
    private static final long serialVersionUID = -2157875174322890127L;

    @ApiModelProperty("评价id")
    private Long commentId;

    @ApiModelProperty("创建时间")
    private Date createdAt;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("买家用户名")
    private String userName;

    @ApiModelProperty("买家头像")
    private String userAvatar;

    @ApiModelProperty("买家id")
    private Long userId;

    @ApiModelProperty("评分")
    private Integer rate;

    @ApiModelProperty("评价内容")
    private String context;

    @ApiModelProperty("评价状态")
    private Integer status;

    @ApiModelProperty("评价图片")
    private Map<String, Object> images;

    @ApiModelProperty("额外信息")
    private Map<String, Object> extra;
}
