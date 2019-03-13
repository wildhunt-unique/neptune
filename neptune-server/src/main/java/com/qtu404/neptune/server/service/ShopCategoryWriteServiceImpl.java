package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.ShopCategory;
import com.qtu404.neptune.domain.service.ShopCategoryWriteService;
import com.qtu404.neptune.server.dao.ShopCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:42
 */
@Service
public class ShopCategoryWriteServiceImpl implements ShopCategoryWriteService {
    private final ShopCategoryDao shopCategoryDao;

    @Autowired
    public ShopCategoryWriteServiceImpl(ShopCategoryDao shopCategoryDao) {
        this.shopCategoryDao = shopCategoryDao;
    }

    @Override
    public Boolean create(ShopCategory toCreate) {
        return shopCategoryDao.save(toCreate);
    }

    @Override
    public Boolean update(ShopCategory toUpdate) {
        return shopCategoryDao.update(toUpdate);
    }
}
