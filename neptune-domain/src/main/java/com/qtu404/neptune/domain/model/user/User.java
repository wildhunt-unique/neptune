package com.qtu404.neptune.domain.model.user;

import com.qtu404.neptune.util.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseModel implements Serializable {
    private static final long serialVersionUID = 2805681990419021328L;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 权限
     */
    private String rolesJson;

    /**
     * 标签信息
     */
    private String tagsJson;
}
