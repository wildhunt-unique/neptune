package com.qtu404.neptune.util.model;

import com.google.common.base.Throwables;
import com.qtu404.neptune.util.model.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/26 下午7:15
 */
@Slf4j
public abstract class Executor {
    public static <P, R> Response<R> execute(P param, Function<P, R> function) {
        try {
            log.info("Request:{}.Value:{}", param.getClass().getSimpleName(), param.toString());
            checkParam(param);
            return Response.success(function.apply(param));
        } catch (Throwable e) {
            return logAndResponseFail(e, param, function);
        }
    }

    private static <R, P> Response<R> logAndResponseFail(Throwable e, P param, Function<P, R> function) {
        log.error("failed to {} by {}, cause:{}", "", param, Throwables.getStackTraceAsString(e));
        if (e instanceof IllegalArgumentException) {
            return Response.fail(e.getMessage(), Response.ARGUMENT_ERROR);
        } else if (e instanceof ServiceException) {
            return Response.fail(e.getMessage(), Response.SERVICE_ERROR);
        } else {
            return Response.fail("error");
        }
    }

    private static <P> void checkParam(P param) {
        if (param instanceof AbstractRequest) {
            ((AbstractRequest) param).checkParam();
        }
    }
}
