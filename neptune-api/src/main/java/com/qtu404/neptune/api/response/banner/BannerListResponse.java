package com.qtu404.neptune.api.response.banner;

import com.qtu404.neptune.util.model.AbstractRequest;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@ApiModel("广告图list")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerListResponse implements Serializable {
    private static final long serialVersionUID = -5151519051147976294L;

    private List<BannerThinResponse> list;
}
