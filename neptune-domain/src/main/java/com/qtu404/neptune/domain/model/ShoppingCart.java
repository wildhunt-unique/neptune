package com.qtu404.neptune.domain.model;

import com.qtu404.neptune.util.model.BaseModel;
import com.qtu404.neptune.util.model.MyJSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShoppingCart extends BaseModel implements Serializable {
    private static final long serialVersionUID = -3653355157384063772L;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("商品id")
    private Long itemId;

    @ApiModelProperty("商品名")
    private String itemName;

    @ApiModelProperty("商品图")
    private String itemImage;

    @ApiModelProperty("数量")
    private Integer quantity;

    @ApiModelProperty("单价")
    private Integer price;

    @ApiModelProperty("商品销售属性")
    private String itemAttr;

    @ApiModelProperty("商品销售属性，不会存数据库")
    private Map<String, Object> itemAttribute;

    public void setItemAttr(String itemAttr) {
        this.itemAttr = itemAttr;
        if (!StringUtils.isEmpty(itemAttr)) {
            itemAttribute = MyJSON.jsonStringToMap(itemAttr);
        } else {
            itemAttribute = new HashMap<>();
        }
    }

    public void setItemAttribute(Map<String, Object> itemAttribute) {
        this.itemAttribute = itemAttribute;
        if (itemAttribute == null || itemAttribute.isEmpty()) {
            this.itemAttr = null;
        } else {
            this.itemAttr = MyJSON.toJSON(itemAttribute);
        }
    }
}
