package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午4:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Item extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1674066086270980203L;

    /**
     * 类目id
     */
    @ApiModelProperty("类目id")
    private Long categoryId;

    /**
     * 商品编码
     */
    @ApiModelProperty("商品编码")
    private String itemCode;

    /**
     * 店铺id
     */
    @ApiModelProperty("店铺id")
    private Long shopId;

    /**
     * 店铺名
     */
    @ApiModelProperty("店铺名")
    private String shopName;

    /**
     * 商品名
     */
    @ApiModelProperty("商品名")
    private String name;

    /**
     * 商品广告
     */
    private String advertise;

    /**
     * 主图
     */
    private String mainImage;

    /**
     * 视频链接
     */
    private String videoUrl;

    /**
     * 商品类型
     */
    private Integer type;

    /**
     * 商品属性
     */
    private String attributesJson;

    /**
     * 额外信息
     */
    private String extraJson;

    /**
     * 价格
     */
    private Double price;

    /**
     * 库存
     */
    private Long inventory;
}
