package com.qtu404.neptune.api.request.banner;

import com.qtu404.neptune.util.model.AbstractRequest;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@ApiModel("广告图list")
@Data
@EqualsAndHashCode(callSuper = true)
public class BannerListRequest extends AbstractRequest implements Serializable {
    private static final long serialVersionUID = -6227310602103357859L;
}
