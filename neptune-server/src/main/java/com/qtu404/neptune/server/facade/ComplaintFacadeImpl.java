package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.qtu404.neptune.api.facade.ComplaintFacade;
import com.qtu404.neptune.api.request.complaint.ComplaintCreateRequest;
import com.qtu404.neptune.api.request.complaint.ComplaintPagingRequest;
import com.qtu404.neptune.api.response.complaint.ComplaintThinResponse;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.model.Complaint;
import com.qtu404.neptune.domain.model.Shop;
import com.qtu404.neptune.domain.model.User;
import com.qtu404.neptune.domain.service.ComplaintReadService;
import com.qtu404.neptune.domain.service.ComplaintWriteService;
import com.qtu404.neptune.domain.service.ShopReadService;
import com.qtu404.neptune.domain.service.UserReadService;
import com.qtu404.neptune.server.converter.ComplaintConverter;
import com.qtu404.neptune.util.model.AssertUtil;
import com.qtu404.neptune.util.model.Paging;
import com.qtu404.neptune.util.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/9
 */
@Service(interfaceClass = ComplaintFacade.class)
@Component
public class ComplaintFacadeImpl implements ComplaintFacade {
    private final ComplaintReadService complaintReadService;
    private final ComplaintWriteService complaintWriteService;
    private final UserReadService userReadService;
    private final ShopReadService shopReadService;
    private final ComplaintConverter complaintConverter;

    @Autowired
    public ComplaintFacadeImpl(ComplaintReadService complaintReadService, ComplaintWriteService complaintWriteService, UserReadService userReadService, ShopReadService shopReadService, ComplaintConverter complaintConverter) {
        this.complaintReadService = complaintReadService;
        this.complaintWriteService = complaintWriteService;
        this.userReadService = userReadService;
        this.shopReadService = shopReadService;
        this.complaintConverter = complaintConverter;
    }

    @Override
    public Response<Long> createComplaint(ComplaintCreateRequest request) {
        return execute(request, param -> {
            User user = this.userReadService.fetchById(request.getUserId());
            AssertUtil.isExist(user, "user");
            Shop shop = this.shopReadService.fetchById(request.getShopId());
            AssertUtil.isExist(shop, "shop");

            Complaint toCreateComplaint = this.complaintConverter.request2Model(request);
            toCreateComplaint.setNickName(user.getNickname());
            toCreateComplaint.setUserMobile(user.getMobile());
            toCreateComplaint.setShopName(shop.getName());
            toCreateComplaint.setShopMobile(shop.getMobile());
            toCreateComplaint.setStatus(DataStatusEnum.NORMAL.getCode());
            this.complaintWriteService.create(toCreateComplaint);
            return toCreateComplaint.getId();
        });
    }

    /**
     * 投诉分页
     *
     * @param request 查询条件
     * @return 分页信息
     */
    @Override
    public Response<Paging<ComplaintThinResponse>> paging(ComplaintPagingRequest request) {
        return execute(request, param -> {
            Paging<Complaint> complaintPaging = this.complaintReadService.paging(request.toMap());
            return new Paging<>(
                    complaintPaging.getTotal(),
                    complaintPaging.getData().stream()
                            .map(this.complaintConverter::model2ThinResponse)
                            .collect(Collectors.toList())
            );
        });
    }
}
