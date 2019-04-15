package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Maps;
import com.qtu404.neptune.api.facade.CommentFacade;
import com.qtu404.neptune.api.request.comment.CommentCreateRequest;
import com.qtu404.neptune.api.request.comment.CommentEnableRequest;
import com.qtu404.neptune.api.request.comment.CommentGetRequest;
import com.qtu404.neptune.api.request.comment.CommentPagingRequest;
import com.qtu404.neptune.api.response.comment.CommentThinResponse;
import com.qtu404.neptune.common.constant.ConstantValues;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.enums.CommentTypeEnum;
import com.qtu404.neptune.domain.model.Comment;
import com.qtu404.neptune.domain.model.Order;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.domain.service.*;
import com.qtu404.neptune.server.converter.CommentConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/26 上午10:54
 */
@Service(interfaceClass = CommentFacade.class)
@Component
@Slf4j
public class CommentFacadeImpl implements CommentFacade {
    private final CommentReadService commentReadService;

    private final CommentWriteService commentWriteService;

    private final CommentConverter commentConverter;

    private final OrderReadService orderReadService;

    private final UserReadService userReadService;

    private final ShopReadService shopReadService;

    @Autowired
    public CommentFacadeImpl(CommentReadService commentReadService, CommentWriteService commentWriteService, CommentConverter commentConverter, OrderReadService orderReadService, UserReadService userReadService, ShopReadService shopReadService) {
        this.commentReadService = commentReadService;
        this.commentWriteService = commentWriteService;
        this.commentConverter = commentConverter;
        this.orderReadService = orderReadService;
        this.userReadService = userReadService;
        this.shopReadService = shopReadService;
    }

    /**
     * 创建评价
     *
     * @param request 创建参数
     * @return 评价id
     */
    @Override
    public Response<Long> createComment(CommentCreateRequest request) {
        return execute(request, param -> {
            // TODO: 2019/3/26 check order status
            Order order = orderReadService.findById(request.getOrderId());
            AssertUtil.isExist(order, "order");
            User user = userReadService.fetchById(request.getUserId());
            AssertUtil.isExist(user, "user");
            Shop shop = shopReadService.fetchById(order.getShopId());
            AssertUtil.isExist(shop,"shop");

            Comment toCreateComment = this.commentConverter.request2Model(request);
            // 设置comment属性
            toCreateComment.setTargetId(request.getOrderId());
            toCreateComment.setType(CommentTypeEnum.ORDER.getCode());
            toCreateComment.setStatus(DataStatusEnum.NORMAL.getCode());
            toCreateComment.setHasPursue(Boolean.FALSE);
            toCreateComment.setParentId(ConstantValues.NO_PARENT);
            toCreateComment.setTopId(ConstantValues.NO_PARENT);
            toCreateComment.setShopId(order.getShopId());

            // 设置用户信息
            toCreateComment.setUserName(user.getNickname());
            toCreateComment.setUserAvatar(user.getAvatar());
            Map<String, Object> extra = Maps.newHashMap();
            extra.put("buyerPhone", user.getMobile());
            extra.put("shopName", shop.getName());
            toCreateComment.setExtra(extra);
            commentWriteService.createComment(toCreateComment);
            return toCreateComment.getId();
        });
    }

    /**
     * 查看评价
     *
     * @param request 评价id
     * @return 评级信息
     */
    @Override
    public Response<CommentThinResponse> getCommentById(CommentGetRequest request) {
        return execute(request, param -> {
            Comment comment = this.commentReadService.findById(request.getCommentId());
            AssertUtil.isExist(comment, "comment");
            return this.commentConverter.model2ThinResponse(comment);
        });
    }

    /**
     * 评价分页信息
     *
     * @param request 分页条件
     * @return 分页结果
     */
    @Override
    public Response<Paging<CommentThinResponse>> paging(CommentPagingRequest request) {
        return execute(request, param -> {
            Map<String, Object> condition = request.toMap();
            if (Objects.nonNull(request.getOrderId())) {
                condition.put("targetId", request.getOrderId());
                condition.put("type", CommentTypeEnum.ORDER.getCode());
            }
            Paging<Comment> commentPaging = this.commentReadService.paging(condition);
            return new Paging<>(
                    commentPaging.getTotal(),
                    commentPaging.getData().stream()
                            .filter(Objects::nonNull)
                            .map(this.commentConverter::model2ThinResponse)
                            .collect(Collectors.toList())
            );
        });
    }

    /**
     * 评价显示/隐藏
     * @param request 参数
     * @return 是否操作成功
     */
    @Override
    public Response<Boolean> enableComment(CommentEnableRequest request) {
        return execute(request, param -> {
            Comment comment = this.commentReadService.findById(request.getCommentId());
            AssertUtil.isExist(comment,"comment");
            DataStatusEnum.validate(request.getStatus());
            comment.setStatus(request.getStatus());
            this.commentWriteService.update(comment);
            return Boolean.TRUE;
        });
    }
}
