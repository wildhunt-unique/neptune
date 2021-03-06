package com.qtu404.neptune.api.request.comment;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date {DATE}
 */
@ApiModel("评价显示或者隐藏")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentEnableRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 8987739589552351777L;

    @ApiModelProperty(value = "评价id", required = true)
    private Long commentId;

    @ApiModelProperty(value = "1:显示  -2:隐藏", required = true)
    private Integer status;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(commentId, "comment.id");
        ParamUtil.nonNull(status, "status");
    }
}
