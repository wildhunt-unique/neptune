package com.qtu404.neptune.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午7:03
 */
@AllArgsConstructor
@Getter
public enum SwitchStatusEnum {
    ACTIVE(1, "有效的"),
    INACTIVE(-1, "无效的"),
    ;

    private final int code;
    private final String desc;
}
