package com.qtu404.neptune.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 下午3:54
 */
@AllArgsConstructor
@Getter
public enum CommentTypeEnum {
    ORDER(1, "订单")
    ;

    public Boolean validate(Integer code) {
        if (Objects.isNull(code)) return Boolean.FALSE;
        for (CommentTypeEnum typeEnum : CommentTypeEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private final int code;
    private final String desc;
}
