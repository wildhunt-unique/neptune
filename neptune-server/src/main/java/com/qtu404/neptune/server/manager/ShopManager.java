package com.qtu404.neptune.server.manager;

import com.google.common.collect.Maps;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.model.TagBinding;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.server.dao.ShopDao;
import com.qtu404.neptune.server.dao.TagBindingDao;
import com.qtu404.neptune.server.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/23 下午8:02
 */
@Component
public class ShopManager {
    private final UserDao userDao;

    private final ShopDao shopDao;

    private final TagBindingDao tagBindingDao;

    @Autowired
    public ShopManager(UserDao userDao, ShopDao shopDao, TagBindingDao tagBindingDao) {
        this.userDao = userDao;
        this.shopDao = shopDao;
        this.tagBindingDao = tagBindingDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createShop(User seller, Shop toCreate, List<TagBinding> toCreateTagBinding) {
        shopDao.save(toCreate);
        if (!CollectionUtils.isEmpty(toCreateTagBinding)) {
            tagBindingDao.save(toCreateTagBinding);
        }
        if (Objects.isNull((seller.getExtra()))) {
            Map<String, Object> extra = Maps.newHashMap();
            extra.put(ConstantValues.SHOP_ID_KEY, toCreate.getId());
            seller.setExtra(extra);
        } else {
            seller.getExtra().put(ConstantValues.SHOP_ID_KEY, toCreate.getId());
        }

        userDao.update(seller);
    }
}
