package com.qtu404.neptune.api.request.upload;

import com.qtu404.neptune.util.model.AbstractRequest;
import com.qtu404.neptune.util.model.ParamUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/12
 */
@ApiModel("上传文件")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class FileUpLoadRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = 6600561297594804950L;

    @ApiModelProperty(value = "文件名",required = true)
    private String fileName;

    @ApiModelProperty(value = "文件流",required = true)
    private InputStream inputStream;

    @Override
    public void checkParam() {
        super.checkParam();
        ParamUtil.nonNull(fileName,"file.name");
        ParamUtil.nonNull(inputStream,"input.stream");
    }
}

