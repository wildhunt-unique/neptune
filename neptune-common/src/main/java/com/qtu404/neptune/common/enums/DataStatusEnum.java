package com.qtu404.neptune.common.enums;

import java.util.Objects;

public enum DataStatusEnum {
    NORMAL(1, "正常"),
    LOCK(-1, "锁定"),
    FREEZE(-2, "冻结"),
    DELETE(-3, "删除");

    private final int code;
    private final String desc;

    DataStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static void validate(Integer code) {
        if (Objects.isNull(code)) throw new IllegalArgumentException("illegal.status");
        for (DataStatusEnum typeEnum : DataStatusEnum.values()) {
            if (code.equals(typeEnum.getCode())) {
                return;
            }
        }
        throw new IllegalArgumentException("illegal.status");
    }

    @Override
    public String toString() {
        return this.desc;
    }

    public int getCode() {
        return this.code;
    }
}
