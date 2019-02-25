package com.qtu404.neptune.util.model;

import lombok.Data;

import java.util.Date;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:18
 */
@Data
public abstract class BaseModel {
    /**
     * id
     */
    private Long id;

    /**
     * 额外信息
     */
    private String extraJson;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 状态
     */
    private Integer status;
}
