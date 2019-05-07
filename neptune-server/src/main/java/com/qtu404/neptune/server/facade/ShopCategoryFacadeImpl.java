package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.qtu404.neptune.api.facade.ShopCategoryFacade;
import com.qtu404.neptune.api.request.shop.ShopCategoryCreateRequest;
import com.qtu404.neptune.api.request.shop.ShopCategoryListRequest;
import com.qtu404.neptune.api.request.shop.ShopCategoryUpdateRequest;
import com.qtu404.neptune.api.response.shop.ShopCategoryListResponse;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.model.ShopCategory;
import com.qtu404.neptune.domain.service.ShopCategoryReadService;
import com.qtu404.neptune.domain.service.ShopCategoryWriteService;
import com.qtu404.neptune.domain.service.ShopReadService;
import com.qtu404.neptune.server.converter.ShopCategoryConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/1 上午10:43
 */
@Slf4j
@Service(interfaceClass = ShopCategoryFacade.class)
@Component
public class ShopCategoryFacadeImpl implements ShopCategoryFacade {
    private final ShopCategoryWriteService shopCategoryWriteService;

    private final ShopCategoryReadService shopCategoryReadService;

    private final ShopReadService shopReadService;

    private final ShopCategoryConverter shopCategoryConverter;

    @Autowired
    public ShopCategoryFacadeImpl(ShopCategoryWriteService shopCategoryWriteService, ShopCategoryReadService shopCategoryReadService, ShopReadService shopReadService, ShopCategoryConverter shopCategoryConverter) {
        this.shopCategoryWriteService = shopCategoryWriteService;
        this.shopCategoryReadService = shopCategoryReadService;
        this.shopReadService = shopReadService;
        this.shopCategoryConverter = shopCategoryConverter;
    }

    @Override
    public Response<Long> create(ShopCategoryCreateRequest request) {
        return execute(request, param -> {
            ShopCategory toCreate = this.shopCategoryConverter.request2Model(request);

            Shop shop = this.shopReadService.fetchById(request.getShopId());
            AssertUtil.isExist(shop, "shop");

            if (Objects.isNull(toCreate.getPid()) || toCreate.getPid().equals(ConstantValues.NO_PARENT)) {
                toCreate.setPid(ConstantValues.NO_PARENT);
                toCreate.setLevel(ConstantValues.ROOT_LEVEL);
            } else {
                ShopCategory parent = this.shopCategoryReadService.fetchById(toCreate.getPid());
                AssertUtil.isExist(parent, "parent.category");
                toCreate.setLevel(parent.getLevel() + 1);
            }

            if (Objects.isNull(toCreate.getSortIndex())) {
                toCreate.setSortIndex(ConstantValues.DEFAULT_INDEX);
            }

            toCreate.setStatus(DataStatusEnum.NORMAL.getCode());
            if (this.shopCategoryWriteService.create(toCreate)) {
                return toCreate.getId();
            } else {
                throw new ServiceException("shop.category.create.fail");
            }
        });
    }

    @Override
    public Response<Boolean> update(ShopCategoryUpdateRequest request) {
        return execute(request, param -> {
            ShopCategory toUpdate = this.shopCategoryConverter.request2Model(request);
            DataStatusEnum.validate(toUpdate.getStatus());
            this.shopCategoryWriteService.update(toUpdate);
            return Boolean.TRUE;
        });
    }
}
