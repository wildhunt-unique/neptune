package com.qtu404.neptune.util.model;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/26 下午7:15
 */
@Slf4j
public abstract class Executor {
    public static <P, R> Response<R> execute(P param, Function<P, R> function) {
        try {
            checkParam(param);
            return Response.success(function.apply(param));
        } catch (Throwable e) {
            return logAndResponseFail(e, param, function);
        }
    }

    private static <R, P> Response<R> logAndResponseFail(Throwable e, P param, Function<P, R> function) {
        log.error("failed to {} by {}, cause:{}", "", param, Throwables.getStackTraceAsString(e));
        String message = "error";
        if (e instanceof IllegalArgumentException) {
            message = e.getMessage();
            return Response.fail(message, Response.ARGUMENT_ERROR);
        } else if (e instanceof ServiceException) {
            message = e.getMessage();
            return Response.fail(message, Response.SERVICE_ERROR);
        }
        return Response.fail(message);
    }

    private static <P> void checkParam(P param) {
        if (param instanceof AbstractRequest) {
            ((AbstractRequest) param).checkParam();
        }
    }
}
