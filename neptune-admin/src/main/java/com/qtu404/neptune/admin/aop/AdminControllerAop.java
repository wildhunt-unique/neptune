package com.qtu404.neptune.admin.aop;

import com.qtu404.neptune.web.common.aop.TranslateHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/26 上午11:20
 */

@Slf4j
@Component
@Aspect
public class AdminControllerAop {
    private final TranslateHandler translateHandler;

    @Autowired
    public AdminControllerAop(TranslateHandler translateHandler) {
        this.translateHandler = translateHandler;
    }

    @Pointcut("execution(* com.qtu404.neptune.web.common.controller.*.*(..)) || execution(* com.qtu404.neptune.admin.controller.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return this.translateHandler.translateError(joinPoint);
    }

}
