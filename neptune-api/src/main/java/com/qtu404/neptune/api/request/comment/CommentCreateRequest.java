package com.qtu404.neptune.api.request.comment;

import com.qtu404.neptune.util.model.AbstractRequest;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:59
 */
@ApiModel("创建评价")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 6605219474814443757L;

    private Long userId;

    @Override
    public void checkParam() {
        super.checkParam();
    }
}
