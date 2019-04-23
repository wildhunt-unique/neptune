package com.qtu404.neptune.api.request.comment;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.sms.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 下午4:16
 */
@ApiModel("查看评价")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentGetRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -7336360310889953652L;

    @ApiModelProperty("评价id")
    private Long commentId;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(commentId, "comment.id");
    }
}
