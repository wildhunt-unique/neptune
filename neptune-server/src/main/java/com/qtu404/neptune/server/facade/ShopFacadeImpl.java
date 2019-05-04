package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.qtu404.neptune.api.request.shop.*;
import com.qtu404.neptune.api.response.item.ItemThinResponse;
import com.qtu404.neptune.api.response.shop.ShopCategoryDetailResponse;
import com.qtu404.neptune.api.response.shop.ShopCategoryListResponse;
import com.qtu404.neptune.api.response.shop.ShopDetailResponse;
import com.qtu404.neptune.api.response.shop.ShopThinResponse;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.enums.TagTypeEnum;
import com.qtu404.neptune.domain.enums.UserTypeEnum;
import com.qtu404.neptune.domain.enums.ShopTypeEnum;
import com.qtu404.neptune.domain.model.*;
import com.qtu404.neptune.domain.service.*;
import com.qtu404.neptune.server.converter.ItemConverter;
import com.qtu404.neptune.server.converter.ShopCategoryConverter;
import com.qtu404.neptune.server.converter.ShopConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.exception.ServiceException;
import com.qtu404.neptune.util.model.ParamUtil;
import com.qtu404.neptune.api.facade.ShopFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    private final UserReadService userReadService;

    private final ShopConverter shopConverter;

    private final ItemReadService itemReadService;

    private final ShopCategoryReadService shopCategoryReadService;

    private final ShopCategoryConverter shopCategoryConverter;

    private final ItemConverter itemConverter;

    private final TagReadService tagReadService;

    private final TagBindingReadService tagBindingReadService;

    private final TagBindingWriteService tagBindingWriteService;

    @Autowired
    public ShopFacadeImpl(ShopReadService shopReadService, ShopWriteService shopWriteService, UserReadService userReadService, ShopConverter shopConverter, ItemReadService itemReadService, ShopCategoryReadService shopCategoryReadService, ShopCategoryConverter shopCategoryConverter, ItemConverter itemConverter, TagReadService tagReadService, TagBindingWriteService tagBindingWriteService, TagBindingReadService tagBindingReadService) {
        this.shopReadService = shopReadService;
        this.shopWriteService = shopWriteService;
        this.userReadService = userReadService;
        this.shopConverter = shopConverter;
        this.itemReadService = itemReadService;
        this.shopCategoryReadService = shopCategoryReadService;
        this.shopCategoryConverter = shopCategoryConverter;
        this.itemConverter = itemConverter;
        this.tagReadService = tagReadService;
        this.tagBindingWriteService = tagBindingWriteService;
        this.tagBindingReadService = tagBindingReadService;
    }

    /**
     * 创建店铺
     *
     * @param request 请求参数
     * @return 店铺id
     */
    @Override
    public Response<Long> createShop(ShopCreateRequest request) {
        return execute(request, param -> {
            // 卖家校验
            User seller = this.userReadService.fetchById(request.getUserId());
            AssertUtil.isExist(seller, "user");
            if (!ObjectUtils.nullSafeEquals(seller.getStatus(), DataStatusEnum.NORMAL.getCode())) {
                throw new IllegalArgumentException("user.status.error");
            }
            if (ObjectUtils.nullSafeEquals(seller.getType(), UserTypeEnum.SELLER.getCode())) {
                throw new IllegalArgumentException("user.have.multiple.shop");
            }
            seller.setType(UserTypeEnum.SELLER.getCode());

            // 店铺创建初始化
            Shop toCreate = this.shopConverter.createRequest2Model(request);
            if (Objects.isNull(toCreate.getMobile()) && Objects.nonNull(seller.getMobile())) {
                toCreate.setMobile(seller.getMobile());
            }
            if (Objects.isNull(toCreate.getUserName()) && Objects.nonNull(seller.getName())) {
                toCreate.setUserName(seller.getName());
            }
            toCreate.setStatus(DataStatusEnum.FREEZE.getCode());
            toCreate.setType(ShopTypeEnum.SHOP.getCode());

            List<TagBinding> toCreateTagBinding = null;
            // 绑定标签
            if (!CollectionUtils.isEmpty(request.getTagIds())) {
                toCreateTagBinding = this.tagReadService.findByIds(request.getTagIds()).stream()
                        .filter(tag -> tag.getStatus().equals(DataStatusEnum.NORMAL.getCode()))
                        .map(tag -> {
                            TagBinding toCreateBinding = new TagBinding();
                            toCreateBinding.setTagId(tag.getId());
                            toCreateBinding.setType(TagTypeEnum.SHOP.getCode());
                            toCreateBinding.setStatus(DataStatusEnum.NORMAL.getCode());
                            return toCreateBinding;
                        }).collect(Collectors.toList());
            }
            this.shopWriteService.createShop(seller, toCreate, toCreateTagBinding);
            return toCreate.getId();
        });
    }

    /**
     * 更新店铺信息
     *
     * @param request 请求参数
     * @return 是否成功
     */
    @Override
    public Response<Boolean> updateShopInfo(ShopUpdateRequest request) {
        return execute(request, param -> {
            Shop toUpdate = this.shopConverter.updateRequest2Model(request);

            Shop existShop = this.shopReadService.fetchById(request.getShopId());
            ParamUtil.nonExist(existShop, "shop");

            User operator = this.userReadService.fetchById(request.getOperator());
            ParamUtil.nonExist(operator, "user");

            // 不是店主
            if (!operator.getId().equals(existShop.getUserId())) {
                // 也不是管理员
                if (!operator.getType().equals(UserTypeEnum.ADMIN.getCode())) {
                    throw new IllegalArgumentException("illegal.op");
                }
            }

            if (!CollectionUtils.isEmpty(request.getTagIds())) {
                this.tagBindingWriteService.batchSetStatusByTargetIdAndType(toUpdate.getId(), TagTypeEnum.SHOP.getCode(), DataStatusEnum.DELETE.getCode());
                List<Tag> tagList = this.tagReadService.findByIds(request.getTagIds()).stream()
                        .filter(tag -> tag.getStatus().equals(DataStatusEnum.NORMAL.getCode()))
                        .collect(Collectors.toList());

                List<TagBinding> toCreateBindingList = tagList.stream()
                        .map(tag -> {
                            TagBinding tagBinding = new TagBinding();
                            tagBinding.setStatus(DataStatusEnum.NORMAL.getCode());
                            tagBinding.setType(TagTypeEnum.SHOP.getCode());
                            tagBinding.setTagId(tag.getId());
                            tagBinding.setTargetId(toUpdate.getId());
                            return tagBinding;
                        }).collect(Collectors.toList());
                this.tagBindingWriteService.batchCreate(toCreateBindingList);
            }

            // 校验状态是否合法
            if (Objects.nonNull(toUpdate.getStatus())) {
                DataStatusEnum.validate(toUpdate.getStatus());
                if (toUpdate.getStatus().equals(DataStatusEnum.DELETE.getCode())) {
                    this.tagBindingWriteService.batchSetStatusByTargetIdAndType(toUpdate.getId(), TagTypeEnum.SHOP.getCode(), DataStatusEnum.DELETE.getCode());
                }
            }

            // 进行更新
            return this.shopWriteService.updateShop(toUpdate);
        });
    }

    /**
     * web端获得店铺详情
     *
     * @param request 店铺id
     * @return 店铺详情
     */
    @Override
    public Response<ShopDetailResponse> getShopDetail(ShopDetailRequest request) {
        return execute(request, param -> {
            Shop shop = this.shopReadService.fetchById(request.getShopId());
            AssertUtil.isExist(shop, "shop");
            if (!shop.getStatus().equals(DataStatusEnum.NORMAL.getCode())) {
                throw new ServiceException("shop.not.open");
            }

            // TODO: 2019/3/13 batch query item by category ids 
            ShopDetailResponse response = this.shopConverter.model2DetailResponse(shop);
            response.setShopCategoryDetailResponseList(this.shopCategoryReadService.findByShopId(request.getShopId()).stream()
                    .filter(e -> e.getStatus().equals(DataStatusEnum.NORMAL.getCode()))
                    .map(e -> {
                        ShopCategoryDetailResponse shopCategoryDetailResponse = this.shopCategoryConverter.model2DetailResponse(e);
                        shopCategoryDetailResponse.setItemThinResponseList(this.itemReadService.findByCategoryId(e.getId()).stream()
                                .filter(item -> item.getStatus().equals(DataStatusEnum.NORMAL.getCode()) || item.getStatus().equals(DataStatusEnum.LOCK.getCode()))
                                .map(this.itemConverter::model2ThinResponse)
                                .collect(Collectors.toList())
                        );
                        return shopCategoryDetailResponse;
                    })
                    .collect(Collectors.toList())
            );
            return response;
        });
    }

    /**
     * 店铺分页
     *
     * @param request 查询条件
     * @return 分页信息
     */
    @Override
    public Response<Paging<ShopThinResponse>> shopPaging(ShopPagingRequest request) {
        return execute(request, param -> {
            Map<String, Object> params = request.toMap();

            if (Objects.nonNull(request.getTagId())) {
                Set<Long> shopIds = this.tagBindingReadService
                        .findByTagIdAndTypeCheckStatus(request.getTagId(), TagTypeEnum.SHOP.getCode(), DataStatusEnum.NORMAL.getCode())
                        .stream()
                        .map(TagBinding::getTargetId)
                        .collect(Collectors.toSet());

                if (CollectionUtils.isEmpty(shopIds)) {
                    return Paging.empty();
                } else {
                    params.put("ids", shopIds);
                }
            }

            if (Objects.nonNull(request.getShopId())) {
                params.put("id", request.getShopId());
            }

            Paging<Shop> shopPaging = this.shopReadService.paging(params);
            return new Paging<>(
                    shopPaging.getTotal(),
                    shopPaging.getData().stream()
                            .map(this.shopConverter::model2ThinResponse)
                            .collect(Collectors.toList())
            );
        });
    }

    /**
     * // TODO: 2019/3/20 move to ShopCategoryFacade
     * 查询店铺类目列表
     *
     * @param request 请求参数
     * @return 店铺类目列表
     */
    @Override
    public Response<ShopCategoryListResponse> queryCategoryList(ShopCategoryQueryRequest request) {
        return execute(request, param -> {
            Map<String, Object> condition = request.toMap();
            condition.put("id", request.getCategoryId());
            List<ShopCategory> categoryList = this.shopCategoryReadService.list(condition);
            ArrayListMultimap<Long, Item> categoryIdToItemList = ArrayListMultimap.create();
            if (request.getWithItemInfo()) {
                List<Item> items = this.itemReadService.findByCategoryIds(categoryList.stream().map(ShopCategory::getId).collect(Collectors.toList()));
                items.stream()
                        .filter(Objects::nonNull)
                        .filter(item -> item.getStatus().equals(DataStatusEnum.NORMAL.getCode()) || item.getStatus().equals(DataStatusEnum.LOCK.getCode()))
                        .forEach(item -> categoryIdToItemList.put(item.getCategoryId(), item));
            }
            return ShopCategoryListResponse.builder().categoryList(categoryList.stream()
                    .filter(category -> category.getStatus().equals(DataStatusEnum.NORMAL.getCode()))
                    .map(category -> {
                        ShopCategoryDetailResponse response = this.shopCategoryConverter.model2DetailResponse(category);
                        if (request.getWithItemInfo()) {
                            List<Item> items = categoryIdToItemList.get(category.getId());
                            List<ItemThinResponse> itemThinResponseList = Objects.isNull(items) ?
                                    Lists.newArrayList() :
                                    items.stream().map(itemConverter::model2ThinResponse).collect(Collectors.toList());
                            response.setItemThinResponseList(itemThinResponseList);
                        }
                        return response;
                    })
                    .collect(Collectors.toList())
            ).build();
        });
    }
}
