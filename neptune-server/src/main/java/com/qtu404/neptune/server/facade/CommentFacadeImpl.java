package com.qtu404.neptune.server.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.qtu404.neptune.api.facade.CommentFacade;
import com.qtu404.neptune.api.request.comment.CommentCreateRequest;
import com.qtu404.neptune.domain.service.CommentReadService;
import com.qtu404.neptune.domain.service.CommentWriteService;
import com.qtu404.neptune.server.converter.CommentConverter;
import com.qtu404.neptune.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    public CommentFacadeImpl(CommentReadService commentReadService, CommentWriteService commentWriteService, CommentConverter commentConverter) {
        this.commentReadService = commentReadService;
        this.commentWriteService = commentWriteService;
        this.commentConverter = commentConverter;
    }

    @Override
    public Response<Long> createComment(CommentCreateRequest request) {
        return execute(request, param -> {
            // TODO: 2019/3/26 to impl
            return null;
        });
    }
}
