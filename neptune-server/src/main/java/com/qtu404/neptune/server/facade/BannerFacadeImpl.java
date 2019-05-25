package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.qtu404.neptune.api.facade.BannerFacade;
import com.qtu404.neptune.api.request.banner.*;
import com.qtu404.neptune.api.response.banner.BannerListResponse;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.enums.BannerTypeEnum;
import com.qtu404.neptune.domain.model.Banner;
import com.qtu404.neptune.domain.model.Item;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.service.BannerReadService;
import com.qtu404.neptune.domain.service.BannerWriteService;
import com.qtu404.neptune.domain.service.ItemReadService;
import com.qtu404.neptune.domain.service.ShopReadService;
import com.qtu404.neptune.server.converter.BannerConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/19
 */
@Component
@Service(interfaceClass = BannerFacade.class)
public class BannerFacadeImpl implements BannerFacade {
    private final BannerWriteService bannerWriteService;
    private final BannerReadService bannerReadService;
    private final BannerConverter bannerConverter;
    private final ShopReadService shopReadService;
    private final ItemReadService itemReadService;

    @Autowired
    public BannerFacadeImpl(BannerWriteService bannerWriteService, BannerReadService bannerReadService, BannerConverter bannerConverter, ShopReadService shopReadService, ItemReadService itemReadService) {
        this.bannerWriteService = bannerWriteService;
        this.bannerReadService = bannerReadService;
        this.bannerConverter = bannerConverter;
        this.shopReadService = shopReadService;
        this.itemReadService = itemReadService;
    }

    /**
     * 启用、禁用 广告图
     *
     * @param request 请求参数
     * @return 是否操作成功
     */
    @Override
    public Response<Boolean> enableBanner(BannerEnableRequest request) {
        return execute(request, param -> {
            DataStatusEnum.validate(request.getStatus());
            Banner banner = this.bannerReadService.findById(request.getBannerId());
            AssertUtil.isExist(banner, "banner");
            banner.setStatus(request.getStatus());
            this.bannerWriteService.update(banner);
            return Boolean.TRUE;
        });
    }

    /**
     * 删除广告图
     *
     * @param request 请求参数
     * @return 是否操作成功
     */
    @Override
    public Response<Boolean> remove(BannerRemoveRequest request) {
        return execute(request, param -> {
            Banner banner = this.bannerReadService.findById(request.getBannerId());
            AssertUtil.isExist(banner, "banner");
            return this.bannerWriteService.remove(request.getBannerId());
        });
    }

    /**
     * 更新广告图
     *
     * @param request 请求参数
     * @return 是否操作成功
     */
    @Override
    public Response<Boolean> updateBanner(BannerUpdateRequest request) {
        return execute(request, param -> {
            Banner toUpdate = this.bannerConverter.request2Model(request);
            return this.bannerWriteService.update(toUpdate);
        });
    }

    /**
     * 创建广告图
     *
     * @param request 请求参数
     * @return id
     */
    @Override
    public Response<Long> createBanner(BannerCreateRequest request) {
        return execute(request, param -> {
            Banner toCreate = this.bannerConverter.request2Model(request);
            BannerTypeEnum.validate(request.getType());
            switch (request.getType()) {
                case BannerTypeEnum.PURE_INT:
                    break;
                case BannerTypeEnum.SHOP_INT:
                    Long shopId = Long.valueOf(toCreate.getContent());
                    Shop shop = this.shopReadService.fetchById(shopId);
                    AssertUtil.isExist(shop, "shop");
                    break;
                case BannerTypeEnum.ITEM_INT:
                    Long itemId = Long.valueOf(toCreate.getContent());
                    Item item = this.itemReadService.fetchById(itemId);
                    AssertUtil.isExist(item, "item");
                    break;
                case BannerTypeEnum.URL_INT:
                    AssertUtil.isExist(toCreate.getContent(), "url");
                    break;
            }
            toCreate.setStatus(DataStatusEnum.FREEZE.getCode());
            this.bannerWriteService.create(toCreate);
            return toCreate.getId();
        });
    }

    /**
     * 查看所有广告图
     *
     * @param request 请求参数
     * @return 广告图list
     */
    @Override
    public Response<BannerListResponse> listBanner(BannerListRequest request) {
        return execute(request, param ->
                BannerListResponse.builder().list(
                        this.bannerReadService.list(Maps.newHashMap()).stream()
                                .map(this.bannerConverter::model2thinResponse).collect(Collectors.toList()))
                        .build()
        );
    }

    /**
     * 得到移动端展示的广告图
     *
     * @param request 请求参数
     * @return 广告图list
     */
    @Override
    public Response<BannerListResponse> getMobileBanner(BannerListRequest request) {
        return execute(request, param ->
                BannerListResponse.builder().list(
                        this.bannerReadService.list(ImmutableMap.of("status", DataStatusEnum.NORMAL.getCode())).stream()
                                .map(this.bannerConverter::model2thinResponse).collect(Collectors.toList()))
                        .build()
        );
    }
}
