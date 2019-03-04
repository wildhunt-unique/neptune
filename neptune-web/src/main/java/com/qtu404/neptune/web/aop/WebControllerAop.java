package com.qtu404.neptune.web.aop;

import com.qtu404.neptune.util.model.Response;
import com.qtu404.neptune.web.common.aop.TranslateHandler;
import javafx.scene.transform.Translate;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/26 上午11:20
 */

@Slf4j
@Component
@Aspect
public class WebControllerAop {
    private final TranslateHandler translateHandler;

    @Autowired
    public WebControllerAop(TranslateHandler translateHandler) {
        this.translateHandler = translateHandler;
    }

    @Pointcut("execution(* com.qtu404.neptune.web.common.controller.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return this.translateHandler.translateError(joinPoint);
    }
}
