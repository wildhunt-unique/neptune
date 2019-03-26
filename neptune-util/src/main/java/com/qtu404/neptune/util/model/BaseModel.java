package com.qtu404.neptune.util.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:18
 */
public abstract class BaseModel implements Serializable {
    private static final long serialVersionUID = 8913872946684638933L;

    /**
     * id
     */
    @Getter
    @Setter
    private Long id;

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
    @Getter
    private Map<String, Object> extra;

    /**
     * 额外信息
     */
    @JsonIgnore
    @Getter
    private String extraJson;

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
        if (extra == null || extra.isEmpty()) {
            this.extraJson = null;
        } else {
            this.extraJson = MyJSON.toJSON(extra);
        }
    }

    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
        if (!StringUtils.isEmpty(extraJson)) {
            extra = MyJSON.jsonStringToMap(extraJson);
        } else {
            extra = new HashMap<>();
        }
    }
}
