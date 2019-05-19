package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Banner;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
public interface BannerReadService {
    Banner findById(Long bannerId);

    List<Banner> list(Map<String,Object> condition);
}
