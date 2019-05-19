package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Banner;
import com.qtu404.neptune.domain.service.BannerWriteService;
import com.qtu404.neptune.server.dao.BannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@Service
public class BannerWriteServiceImpl implements BannerWriteService {
    private final BannerDao bannerDao;

    @Autowired
    public BannerWriteServiceImpl(BannerDao bannerDao) {
        this.bannerDao = bannerDao;
    }

    @Override
    public Boolean update(Banner banner) {
        return this.bannerDao.update(banner);
    }

    @Override
    public Boolean remove(Long bannerId) {
        return this.bannerDao.remove(bannerId);
    }

    @Override
    public Boolean create(Banner toCreate) {
        return this.bannerDao.save(toCreate);
    }
}
