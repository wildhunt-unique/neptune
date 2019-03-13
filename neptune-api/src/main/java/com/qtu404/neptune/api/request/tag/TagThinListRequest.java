package com.qtu404.neptune.api.request.tag;

import com.qtu404.neptune.util.model.AbstractRequest;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午2:06
 */
@ApiModel("标签列表")
@Data
@EqualsAndHashCode(callSuper = true)
public class TagThinListRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 5692130419210685278L;
}
