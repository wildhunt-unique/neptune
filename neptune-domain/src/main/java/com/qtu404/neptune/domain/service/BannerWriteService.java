package com.qtu404.neptune.domain.service;

import com.qtu404.neptune.domain.model.Banner;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
public interface BannerWriteService {
    Boolean update(Banner banner);

    Boolean remove(Long bannerId);

    Boolean create(Banner toCreate);
}
