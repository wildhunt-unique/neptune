package com.qtu404.neptune.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/18 下午7:03
 */
@AllArgsConstructor
@Getter
public enum SwitchStatusEnum {
    ACTIVE(1, "有效的"),
    INIT(0, "初始化"),
    INACTIVE(-1, "无效的");

    private final int code;
    private final String desc;

    public static void validate(Integer code) {
        if (Objects.isNull(code)) throw new IllegalArgumentException("illegal.status");
        for (SwitchStatusEnum typeEnum : SwitchStatusEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return;
            }
        }
        throw new IllegalArgumentException("illegal.status");
    }
}
