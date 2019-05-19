package com.qtu404.neptune.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@AllArgsConstructor
@Getter
public enum BannerTypeEnum {
    PURE(0, "纯图，无跳转"),
    SHOP(1, "跳到店家"),
    ITEM(2, "跳到商品"),
    URL(3, "跳到某一网址"),
    ;

    public static void validate(Integer code) {
        if (Objects.isNull(code)) throw new IllegalArgumentException("illegal.banner.type");
        for (BannerTypeEnum typeEnum : BannerTypeEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return;
            }
        }
        throw new IllegalArgumentException("illegal.banner.type");
    }

    private final int code;
    private final String desc;

    public static final int PURE_INT = 0;
    public static final int SHOP_INT = 1;
    public static final int ITEM_INT = 2;
    public static final int URL_INT = 3;

}
