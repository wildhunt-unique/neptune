package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.qtu404.neptune.api.facade.ShopTagFacade;
import com.qtu404.neptune.api.request.tag.TagCreateRequest;
import com.qtu404.neptune.api.request.tag.TagDeleteRequest;
import com.qtu404.neptune.api.request.tag.TagThinListRequest;
import com.qtu404.neptune.api.request.tag.TagUpdateRequest;
import com.qtu404.neptune.api.response.tag.TagThinListResponse;
import com.qtu404.neptune.common.enums.DataStatusEnum;
import com.qtu404.neptune.domain.enums.TagTypeEnum;
import com.qtu404.neptune.domain.model.Tag;
import com.qtu404.neptune.domain.service.TagBindingWriteService;
import com.qtu404.neptune.domain.service.TagReadService;
import com.qtu404.neptune.domain.service.TagWriteService;
import com.qtu404.neptune.server.converter.TagConverter;
import com.qtu404.neptune.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

import static com.qtu404.neptune.util.model.Executor.execute;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:47
 */
@Component
@Slf4j
@Service(interfaceClass = ShopTagFacade.class)
public class TagFacadeImpl implements ShopTagFacade {
    private final TagConverter tagConverter;

    private final TagReadService tagReadService;

    private final TagWriteService tagWriteService;

    private final TagBindingWriteService tagBindingWriteService;

    @Autowired
    public TagFacadeImpl(TagConverter tagConverter, TagReadService tagReadService, TagWriteService tagWriteService, TagBindingWriteService tagBindingWriteService) {
        this.tagConverter = tagConverter;
        this.tagReadService = tagReadService;
        this.tagWriteService = tagWriteService;
        this.tagBindingWriteService = tagBindingWriteService;
    }

    @Override
    public Response<Long> create(TagCreateRequest request) {
        return execute(request, param -> {
            Tag toCreateTag = this.tagConverter.request2Model(request);

            toCreateTag.setType(TagTypeEnum.SHOP.getCode());
            toCreateTag.setStatus(DataStatusEnum.NORMAL.getCode());
            this.tagWriteService.create(toCreateTag);

            return toCreateTag.getId();
        });
    }

    @Override
    public Response<TagThinListResponse> thinList(TagThinListRequest request) {
        return execute(request, param -> {
            TagThinListResponse response = new TagThinListResponse();
            response.setTagThinResponse(this.tagReadService.findByType(TagTypeEnum.SHOP.getCode()).stream()
                    .filter(tag -> tag.getType().equals(TagTypeEnum.SHOP.getCode()) && !tag.getStatus().equals(DataStatusEnum.DELETE.getCode()))
                    .map(this.tagConverter::model2ThinResponse)
                    .collect(Collectors.toList())
            );
            return response;
        });
    }

    @Override
    public Response<Boolean> update(TagUpdateRequest request) {
        return execute(request, param -> {
            Tag toUpdateTag = this.tagConverter.request2Model(request);

            // 设置状态
            if (Objects.nonNull(toUpdateTag.getStatus())) {
                DataStatusEnum.validate(toUpdateTag.getStatus());
                if (toUpdateTag.getStatus().equals(DataStatusEnum.DELETE.getCode())) {
                    this.tagBindingWriteService.batchSetStatusByTagIdAndType(
                            toUpdateTag.getId(),
                            TagTypeEnum.SHOP.getCode(),
                            DataStatusEnum.DELETE.getCode());
                }
            }

            // 进行更新
            return this.tagWriteService.update(toUpdateTag);
        });
    }

    /**
     * 删除标签
     * @param request 标签id
     * @return 是否成功
     */
    @Override
    public Response<Boolean> delete(TagDeleteRequest request) {
         return execute(request, param -> {
             this.tagWriteService.delete(request.getTagId());
             return Boolean.TRUE;
          });
    }
}
