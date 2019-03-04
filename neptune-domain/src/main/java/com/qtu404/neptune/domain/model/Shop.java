package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午9:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Shop extends BaseModel implements Serializable {
    private static final long serialVersionUID = -2274425969788722037L;

    /**
     * 卖家id
     */
    private Long userId;

    /**
     * 卖家名
     */
    private String userName;

    /**
     * 店名
     */
    private String name;

    /**
     * 店铺类型
     */
    private Integer type;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 店铺图片url
     */
    private String imageUrl;

    /**
     * 店铺地址
     */
    private String address;

    /**
     * 店铺标签
     */
    private String tags;

    /**
     * 外部编码
     */
    private String outerId;
}
