package com.qtu404.neptune.common.constant;

import lombok.Data;

@Data
public class ConstantValues {
    /**
     * 当前用户key
     */
    public static final String SESSION_CURRENT_USER_KEY = "CURRENT_USER";

    /**
     * 默认用户头像
     */
    public static final String DEFAULT_AVATAR = "http://static.qtu404.com/nf4slide/assets/user.png";

    /**
     * 最小库存
     */
    public static final Long MIN_INVENTORY = 0L;

    // 店铺类目
    public static final Long NO_PARENT = -1L;
    public static final Integer ROOT_LEVEL = 1;
    public static final Integer DEFAULT_INDEX = 1;

    // 最大留言长度
    public static final Integer MAX_NOTE_LENGTH = 127;
    public static final String UUID_PREFIX = "QTU404_UUID";
    public static final String BLANK = "-";

    public static final String SHOP_ID_KEY = "SHOP_ID";
    public static final String REGISTER_SMS_PREFIX="REGISTER_SMS";
    public static final String LOGIN_SMS_PREFIX = "LOGIN_SMS";
}
