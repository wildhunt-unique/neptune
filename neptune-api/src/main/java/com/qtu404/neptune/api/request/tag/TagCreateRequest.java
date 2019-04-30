package com.qtu404.neptune.api.request.tag;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:58
 */
@ApiModel("创建标签")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TagCreateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -6657376906290722016L;
    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名",required = true)
    private String name;


    /**
     * 内容
     */
    @ApiModelProperty("标签内容")
    private String content;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(this.name,"tag.name");
    }
}
