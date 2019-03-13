package com.qtu404.neptune.util.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/6 下午2:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractPagingRequest extends AbstractRequest {

    @ApiModelProperty("分页请求: 页数")
    private Integer pageNo;

    @ApiModelProperty("分页请求: 一页多少条")
    private Integer pageSize;

    @ApiModelProperty(hidden = true)
    private Integer offset;

    @ApiModelProperty(hidden = true)
    private Integer limit;

    @Override
    public void checkParam() {
        super.checkParam();
        if (Objects.isNull(pageSize) || pageSize < 0) {
            pageSize = 20;
        }
        limit = pageSize;

        if (Objects.isNull(pageNo) || pageNo <= 0) {
            offset = 0;
        } else {
            offset = (pageNo - 1) * pageSize;
        }
    }
}
