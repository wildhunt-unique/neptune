package com.qtu404.neptune.web.common.aop;

import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.util.model.exception.AuthorizationException;
import com.qtu404.neptune.util.model.exception.RestException;
import com.qtu404.neptune.util.model.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/22
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static Properties prop = new Properties();

    static {
        try {
            InputStream input = GlobalExceptionHandler.class.getClassLoader().getResourceAsStream(("message/message_zh.properties"));
            prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 翻译错误
     *
     * @param response 结果信息
     */
    private Response translateError(Response response) {
        if (Objects.nonNull(prop) && Objects.nonNull(response)) {
            // 去翻译文件里找
            String error = prop.getProperty(response.getError());
            if (!StringUtils.isEmpty(error)) {
                response.setError(error);
            }
        }
        return response;
    }

    @ResponseBody
    @ExceptionHandler(value = RestException.class)
    public Response restExceptionHandler(RestException e) {
        return this.translateError(
                Response.fail(e.getMessage(), Response.ARGUMENT_ERROR)
        );
    }

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public Response serviceExceptionHandler(ServiceException e) {
        return this.translateError(
                Response.fail(e.getMessage(), Response.SERVICE_ERROR)
        );
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response otherExceptionHandler(Exception e) {
        return this.translateError(
                Response.fail("error", Response.SERVICE_ERROR)
        );
    }

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public Response authorizationExceptionHandler(AuthorizationException e) {
        return this.translateError(
                Response.fail(e.getMessage(), Response.NO_AUTH)
        );
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Response illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return this.translateError(
                Response.fail(e.getMessage(), Response.ARGUMENT_ERROR)
        );
    }
}
