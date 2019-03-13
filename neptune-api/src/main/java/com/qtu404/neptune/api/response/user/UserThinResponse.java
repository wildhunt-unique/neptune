package com.qtu404.neptune.api.response.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/13 上午10:17
 */
@ApiModel("用户简约信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserThinResponse implements Serializable {
    private static final long serialVersionUID = -6003627496405436933L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long userId;

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private Integer status;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;


    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型 1:2c用户 2:2b用户 3:后端管理员")
    private Integer type;

    /**
     * 头像地址
     */
    @ApiModelProperty("头像地址")
    private String avatar;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String name;

}
