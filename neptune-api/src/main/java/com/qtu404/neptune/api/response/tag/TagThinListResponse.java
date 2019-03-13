package com.qtu404.neptune.api.response.tag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午2:07
 */
@ApiModel("标签列表")
@Data
public class TagThinListResponse implements Serializable {
    private static final long serialVersionUID = -7081900798121873596L;

    @ApiModelProperty("标签简略信息列表")
    private List<TagThinResponse> tagThinResponse;
}
