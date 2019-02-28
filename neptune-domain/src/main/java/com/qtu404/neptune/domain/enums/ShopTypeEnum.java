package com.qtu404.neptune.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:56
 */
@AllArgsConstructor
@Getter
public enum ShopTypeEnum {
    SHOP(1, "店铺"),
    BUSINESS(2, "商家")
    ;

    private final int code;
    private final String desc;

    public Boolean verify(Integer code) {
        if (Objects.isNull(code)) return Boolean.FALSE;
        for (ShopTypeEnum typeEnum : ShopTypeEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public ShopTypeEnum getType(Integer code) {
        if (Objects.isNull(code)) return null;
        for (ShopTypeEnum typeEnum : ShopTypeEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return typeEnum;
            }
        }
        return null;
    }
}
