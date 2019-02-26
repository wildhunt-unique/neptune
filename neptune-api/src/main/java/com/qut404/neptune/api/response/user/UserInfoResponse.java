package com.qut404.neptune.api.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午5:16
 */
@Data
@ApiModel("用户信息")
public class UserInfoResponse implements Serializable {
    private static final long serialVersionUID = 1993318882673254101L;
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("额外信息")
    private String extraJson;
}
