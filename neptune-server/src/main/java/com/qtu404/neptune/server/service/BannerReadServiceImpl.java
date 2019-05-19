package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Banner;
import com.qtu404.neptune.domain.service.BannerReadService;
import com.qtu404.neptune.server.dao.BannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@Service
public class BannerReadServiceImpl implements BannerReadService {
    private final BannerDao bannerDao;

    @Autowired
    public BannerReadServiceImpl(BannerDao bannerDao) {
        this.bannerDao = bannerDao;
    }

    @Override
    public Banner findById(Long bannerId) {
        return this.bannerDao.fetch(bannerId);
    }

    @Override
    public List<Banner> list(Map<String, Object> condition) {
        return this.bannerDao.list(condition);
    }
}
