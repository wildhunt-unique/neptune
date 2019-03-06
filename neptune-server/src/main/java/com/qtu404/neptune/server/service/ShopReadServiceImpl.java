package com.qtu404.neptune.server.service;

import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.service.ShopReadService;
import com.qtu404.neptune.server.dao.ShopDao;
import com.qtu404.neptune.util.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:14
 */
// TODO: 2019/2/28 add cache
@Service
public class ShopReadServiceImpl implements ShopReadService {
    private final ShopDao shopDao;

    @Autowired
    public ShopReadServiceImpl(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    public Shop fetchById(Long shopId) {
        return shopDao.fetch(shopId);
    }

    @Override
    public List<Shop> list(Map<String, Object> toMap) {
        return shopDao.list(toMap);
    }

    @Override
    public Paging<Shop> paging(Map<String, Object> toMap) {
        return shopDao.paging(toMap);
    }
}
