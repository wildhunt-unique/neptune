package com.qtu404.neptune.util.translate;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/21 上午11:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TranslateRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 4548956063607488353L;
    private String from;
    private String to;
    private String content;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(content, "content");
        if (Objects.isNull(from)){
            from = "auto";
        }
        if (Objects.isNull(to)){
            to = "auto";
        }
    }
}
