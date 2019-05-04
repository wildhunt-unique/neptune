package com.qtu404.neptune.api.response.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/4
 */
@Data
public class UserMetaData implements Serializable {
    private static final long serialVersionUID = -8446995074613168705L;
    private Long userId;
    private Integer level;
    private Long shopId;
}
