package com.qtu404.neptune.api.request.user;

import com.qtu404.neptune.util.model.AbstractPagingRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/13 上午10:22
 */
@ApiModel("用户分页请求")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserPagingRequest extends AbstractPagingRequest implements Serializable {
    private static final long serialVersionUID = 6743256848406320107L;

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
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型 1:2c用户 2:2b用户 3:后端管理员")
    private Integer type;

    @Override
    public void checkParam() {
        super.checkParam();
    }
}
