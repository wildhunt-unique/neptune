package com.qtu404.neptune.common.enums;

import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午5:42
 */
public enum UserTypeEnum {
    CUSTOMER(1, "消费者"),
    SELLER(2, "商家");

    private final int code;
    private final String desc;

    UserTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }

    public Boolean verify(Integer code) {
        if (Objects.isNull(code)) return Boolean.FALSE;
        for (UserTypeEnum typeEnum : UserTypeEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public UserTypeEnum getType(Integer code) {
        if (Objects.isNull(code)) return null;
        for (UserTypeEnum typeEnum : UserTypeEnum.values()) {
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
