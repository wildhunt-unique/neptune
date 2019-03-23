package com.qtu404.neptune.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qtu404.neptune.util.model.BaseModel;
import com.qtu404.neptune.util.model.MyJSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午4:40
 */
public class Item extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1674066086270980203L;

    /**
     * 类目id
     */
    @ApiModelProperty("类目id")
    @Getter
    @Setter
    private Long categoryId;

    /**
     * 商品编码
     */
    @ApiModelProperty("商品编码")
    @Getter
    @Setter
    private String itemCode;

    /**
     * 店铺id
     */
    @ApiModelProperty("店铺id")
    @Getter
    @Setter
    private Long shopId;

    /**
     * 店铺名
     */
    @ApiModelProperty("店铺名")
    @Getter
    @Setter
    private String shopName;

    /**
     * 商品名
     */
    @ApiModelProperty("商品名")
    @Getter
    @Setter
    private String name;

    /**
     * 商品广告
     */
    @ApiModelProperty("商品广告")
    @Getter
    @Setter
    private String advertise;

    /**
     * 主图
     */
    @ApiModelProperty("主图")
    @Getter
    @Setter
    private String mainImage;

    /**
     * 视频链接
     */
    @ApiModelProperty("视频链接")
    @Getter
    @Setter
    private String videoUrl;

    /**
     * 商品类型
     */
    @ApiModelProperty("商品类型")
    @Getter
    @Setter
    private Integer type;

    /**
     * 商品属性
     */
    @ApiModelProperty("商品属性")
    @JsonIgnore
    @Getter
    private String attributeJson;

    /**
     * 商品属性，不存数据库
     */
    @Getter
    private Map<String, Object> attribute;

    public void setAttributeJson(String attributeJson) {
        this.attributeJson = attributeJson;
        if (!StringUtils.isEmpty(attributeJson)) {
            attribute = MyJSON.jsonStringToMap(attributeJson);
        } else {
            attribute = new HashMap<>();
        }
    }

    public void setAttribute(Map<String, Object> attribute) {
        this.attribute = attribute;
        if (attribute == null || attribute.isEmpty()) {
            this.attributeJson = null;
        } else {
            this.attributeJson = MyJSON.toJSON(attribute);
        }
    }

    /**
     * 价格
     */
    @Getter
    @Setter
    private Double price;

    /**
     * 库存
     */
    @Getter
    @Setter
    private Long inventory;
}
