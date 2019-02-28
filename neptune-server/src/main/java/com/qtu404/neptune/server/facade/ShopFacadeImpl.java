package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.common.enums.UserTypeEnum;
import com.qtu404.neptune.domain.enums.ShopTypeEnum;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.domain.service.ShopReadService;
import com.qtu404.neptune.domain.service.ShopWriteService;
import com.qtu404.neptune.domain.service.UserReadService;
import com.qtu404.neptune.server.converter.ShopConverter;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.sms.ParamUtil;
import com.qut404.neptune.api.facade.shop.ShopFacade;
import com.qut404.neptune.api.request.shop.ShopCreateRequest;
import com.qut404.neptune.api.request.shop.ShopUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:11
 */
@Slf4j
@Component
@Service(interfaceClass = ShopFacade.class)
public class ShopFacadeImpl implements ShopFacade {
    private final ShopReadService shopReadService;

    private final ShopWriteService shopWriteService;

    private UserReadService userReadService;

    private final ShopConverter shopConverter;

    @Autowired
    public ShopFacadeImpl(ShopReadService shopReadService, ShopWriteService shopWriteService, UserReadService userReadService, ShopConverter shopConverter) {
        this.shopReadService = shopReadService;
        this.shopWriteService = shopWriteService;
        this.userReadService = userReadService;
        this.shopConverter = shopConverter;
    }

    @Override
    public Response<Long> createShop(ShopCreateRequest request) {
        return execute(request, param -> {
            User seller = this.userReadService.fetchById(request.getUserId());
            if (Objects.isNull(seller)) throw new IllegalArgumentException("user.not.exist");

            if (Objects.isNull(seller.getType()) || !ObjectUtils.nullSafeEquals(seller.getType(), UserTypeEnum.SELLER.getCode())) {
                throw new IllegalArgumentException("user.not.seller");
            }

            Shop toCreate = this.shopConverter.createRequest2Model(request);
            if (Objects.isNull(toCreate.getMobile()) && Objects.nonNull(seller.getMobile())) {
                toCreate.setMobile(seller.getMobile());
            }

            if (Objects.isNull(toCreate.getUserName()) && Objects.nonNull(seller.getName())) {
                toCreate.setUserName(seller.getName());
            }

            toCreate.setStatus(DataStatusEnum.LOCK.getCode());
            toCreate.setType(ShopTypeEnum.SHOP.getCode());

            return this.shopWriteService.createShop(toCreate) ? toCreate.getId() : null;
        });
    }

    @Override
    public Response<Boolean> updateShopInfo(ShopUpdateRequest request) {
        return execute(request, param -> {
            Shop toUpdate = this.shopConverter.updateRequest2Model(request);

            Shop existShop = this.shopReadService.fetchById(request.getShopId());
            ParamUtil.nonExist(existShop, "shop");

            User user = this.userReadService.fetchById(request.getUserId());
            ParamUtil.nonExist(user, "user");

            // 校验状态是否合法
            DataStatusEnum.validate(request.getStatus());

            // 不是店主
            if (!user.getId().equals(existShop.getUserId())) {
                // 也不是管理员
                if (!user.getType().equals(UserTypeEnum.ADMIN.getCode())) {
                    throw new IllegalArgumentException("illegal.op");
                }
            }

            // 进行更新
            return this.shopWriteService.updateShop(toUpdate);
        });
    }
}
