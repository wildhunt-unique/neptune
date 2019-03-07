package com.qtu404.neptune.web.common.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.ShopTagFacade;
import com.qtu404.neptune.api.request.tag.TagThinListRequest;
import com.qtu404.neptune.api.response.tag.TagThinListResponse;
import com.qtu404.neptune.util.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/7 下午1:56
 */
@Api(value = "店铺标签通用接口", tags = "标签通用接口")
@RestController
@RequestMapping("api/common/tag/")
public class ShopTagCommonController {
    @Reference
    private ShopTagFacade shopTagFacade;

    @GetMapping("list")
    @ApiOperation("获得店铺标签列表")
    public Response<TagThinListResponse> list(TagThinListRequest request){
        return shopTagFacade.thinList(request);
    }
}
