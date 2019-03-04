package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.qtu404.neptune.api.facade.ItemFacade;
import com.qtu404.neptune.api.request.item.ItemAdjustRequest;
import com.qtu404.neptune.api.request.item.ItemCreateRequest;
import com.qtu404.neptune.api.request.item.ItemUpdateRequest;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.model.Item;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.service.ItemReadService;
import com.qtu404.neptune.domain.service.ItemWriteService;
import com.qtu404.neptune.domain.service.ShopReadService;
import com.qtu404.neptune.server.converter.ItemConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 下午5:28
 */
@Slf4j
@Component
@Service(interfaceClass = ItemFacade.class)
public class ItemFacadeImpl implements ItemFacade {

    private final ItemWriteService itemWriteService;

    private final ItemConverter itemConverter;

    private final ShopReadService shopReadService;

    private final ItemReadService itemReadService;

    @Autowired
    public ItemFacadeImpl(ItemWriteService itemWriteService, ItemConverter itemConverter, ShopReadService shopReadService, ItemReadService itemReadService) {
        this.itemWriteService = itemWriteService;
        this.itemConverter = itemConverter;
        this.shopReadService = shopReadService;
        this.itemReadService = itemReadService;
    }

    @Override
    public Response<Long> createItem(ItemCreateRequest request) {
        return execute(request, param -> {
            Item toCreateItem = this.itemConverter.request2Model(request);

            // 验证shop
            Shop shop = this.shopReadService.fetchById(request.getShopId());
            AssertUtil.isExist(shop, "shop");
            toCreateItem.setShopName(shop.getName());

            toCreateItem.setInventory(ConstantValues.MIN_INVENTORY);
            toCreateItem.setStatus(DataStatusEnum.FREEZE.getCode());
            return this.itemWriteService.createItem(toCreateItem) ? toCreateItem.getId() : null;
        });
    }

    @Override
    public Response<Boolean> update(ItemUpdateRequest request) {
        return execute(request, param -> {
            Item toUpdateItem = this.itemConverter.request2Model(request);

            Item existItem = this.itemReadService.fetchById(toUpdateItem.getId());
            AssertUtil.isExist(existItem, "item");

            if (toUpdateItem.getStatus().equals(DataStatusEnum.NORMAL.getCode())) {
                if (existItem.getInventory() <= ConstantValues.MIN_INVENTORY) {
                    toUpdateItem.setStatus(DataStatusEnum.LOCK.getCode());
                }
            }

            if (this.itemWriteService.update(toUpdateItem)) {
                return Boolean.TRUE;
            } else {
                throw new ServiceException("item.update.fail");
            }
        });
    }

    @Override
    public Response<Boolean> adjust(ItemAdjustRequest request) {
        return execute(request, param -> {
            Item toAdjustItem = this.itemReadService.fetchById(request.getItemId());
            AssertUtil.isExist(toAdjustItem, "item");

            toAdjustItem.setInventory(request.getInventory());
            if (!toAdjustItem.getStatus().equals(DataStatusEnum.FREEZE.getCode())) {
                if (request.getInventory() > 0) {
                    toAdjustItem.setStatus(DataStatusEnum.NORMAL.getCode());
                } else {
                    toAdjustItem.setStatus(DataStatusEnum.LOCK.getCode());
                }
            }

            return this.itemWriteService.update(toAdjustItem);
        });
    }
}
