package com.qtu404.neptune.api.request.shop;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/24
 */
@ApiModel("商品搜索")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopWithSearchItemRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -455752437512652366L;

    @ApiModelProperty("关键词")
    private String keyword;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonExist(keyword, "keyword");
        if (keyword.length() == 0) {
            throw new IllegalArgumentException("keyword.not.empty");
        }
    }
}
