package com.qtu404.neptune.admin.aop;

import com.qtu404.neptune.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/26 上午11:20
 */

@Slf4j
@Component
@Aspect
public class AdminControllerAop {
    private static Properties prop = new Properties();

    static {
        try {
            InputStream input = AdminControllerAop.class.getClassLoader().getResourceAsStream(("message/message_zh.properties"));
            prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Pointcut("execution(* com.qtu404.neptune.web.common.controller.*.*(..)) || execution(* com.qtu404.neptune.admin.controller.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed(joinPoint.getArgs());
        if (result instanceof Response) {
            Response response = (Response) result;
            if (!response.isSuccess()) {
                if (Objects.nonNull(prop)) {
                    String error = prop.getProperty(response.getError());
                    if (StringUtils.isEmpty(error)) {
                        error = "服务器繁忙";
                    }
                    ((Response) result).setError(error);
                }
            }
        }
        return result;
    }
}
