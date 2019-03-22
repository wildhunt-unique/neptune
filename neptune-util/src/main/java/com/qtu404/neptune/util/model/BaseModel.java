package com.qtu404.neptune.util.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:18
 */
// TODO: 2019/3/22 没改完，先别发
public abstract class BaseModel {
    /**
     * id
     */
    @Getter
    @Setter
    private Long id;

    /**
     * 额外信息
     */
    private String extraJson;

    /**
     * 创建时间
     */
    @Getter
    @Setter
    private Date createdAt;

    /**
     * 更新时间
     */
    @Getter
    @Setter
    private Date updatedAt;

    /**
     * 状态
     */
    @Getter
    @Setter
    private Integer status;

    /**
     * 额外信息的map存储
     */
    private Map<String, Object> extra;

    public void setExtraJson(String extraJson) {
//        this.extraJson = extraJson;
        if (!StringUtils.isEmpty(extraJson)) {
            extra = MyJSON.jsonStringToMap(extraJson);
        }
    }
}
