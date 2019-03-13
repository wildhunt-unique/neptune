package com.qtu404.neptune.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:41
 */
@Getter
@AllArgsConstructor
public enum TagTypeEnum {
    SHOP(1, "店铺标签"),
    ;

    private final int code;
    private final String desc;

    public static Boolean validate(Integer code) {
        if (Objects.isNull(code)) return Boolean.FALSE;
        for (TagTypeEnum typeEnum : TagTypeEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public TagTypeEnum getType(Integer code) {
        if (Objects.isNull(code)) return null;
        for (TagTypeEnum typeEnum : TagTypeEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
