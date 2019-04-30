package com.qtu404.neptune.api.request.tag;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/8 下午2:01
 */
@ApiModel("店铺标签更新")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagUpdateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -3573474658944838576L;

    @ApiModelProperty(value = "标签id",required = true)
    private Long tagId;

    /**
     * 标签名
     */
    @ApiModelProperty("标签名")
    private String name;


    /**
     * 内容
     */
    @ApiModelProperty("标签内容")
    private String content;
    
    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.tagId,"tag.id");
    }
}
