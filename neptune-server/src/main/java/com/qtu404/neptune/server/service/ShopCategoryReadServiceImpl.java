package com.qtu404.neptune.server.service;

import com.google.common.collect.Maps;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.model.ShopCategory;
import com.qtu404.neptune.domain.service.ShopCategoryReadService;
import com.qtu404.neptune.server.dao.ShopCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:41
 */
@Service
public class ShopCategoryReadServiceImpl implements ShopCategoryReadService {
    private final ShopCategoryDao shopCategoryDao;

    @Autowired
    public ShopCategoryReadServiceImpl(ShopCategoryDao shopCategoryDao) {
        this.shopCategoryDao = shopCategoryDao;
    }

    @Override
    public ShopCategory fetchById(Long id) {
        return this.shopCategoryDao.fetch(id);
    }

    @Override
    public List<ShopCategory> findByShopId(Long shopId) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("shopId", shopId);
        return this.shopCategoryDao.list(param);
    }
}
