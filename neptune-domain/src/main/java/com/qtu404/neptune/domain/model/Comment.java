package com.qtu404.neptune.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Comment extends BaseModel implements Serializable {
    private static final long serialVersionUID = -3411124045053240221L;

    @ApiModelProperty("评价类型 订单:1")
    private Integer type;

    @ApiModelProperty("目标id")
    private Long targetId;

    @ApiModelProperty("店铺id")
    private Long shopId;

    @ApiModelProperty("根节点")
    private Long topId;

    @ApiModelProperty("父节点")
    private Long parentId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户头像")
    private String userAvatar;

    @ApiModelProperty("评分")
    private Integer rate;

    @ApiModelProperty("评价内容")
    private String context;

    @ApiModelProperty("评价图片")
    @JsonIgnore
    private String imageJson;

    @ApiModelProperty("评价图片")
    private Map<String, Object> images;

    public void setImageJson(String imageJson) {
        this.imageJson = imageJson;
        if (!StringUtils.isEmpty(imageJson)) {
            images = MyJSON.jsonStringToMap(imageJson);
        } else {
            images = new HashMap<>();
        }
    }

    public void setImages(Map<String, Object> images) {
        this.images = images;
        if (images == null || images.isEmpty()) {
            this.imageJson = null;
        } else {
            this.imageJson = MyJSON.toJSON(images);
        }
    }

    @ApiModelProperty("是否有回复")
    private Boolean hasPursue;
}
