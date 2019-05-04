package com.qtu404.neptune.web.common.aop;

import com.qtu404.neptune.common.constant.AccessLevel;
import com.qtu404.neptune.util.model.exception.RestException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

import static com.qtu404.neptune.web.common.util.RequestContext.getAccessLevel;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/4
 */
@Aspect
@Component
@Slf4j
public class AclAspect {
    @Pointcut("@annotation(com.qtu404.neptune.web.common.aop.Acl)")
    public void aclPointCut() {
    }

    ;

    @Around("aclPointCut()")
    public Object before(ProceedingJoinPoint call) throws Throwable {
        MethodSignature signature = (MethodSignature) call.getSignature();
        Method method = signature.getMethod();
        Acl acl = method.getAnnotation(Acl.class);
        if (Objects.nonNull(acl)) {
            if (AccessLevel.BASE < acl.level()) {
                Integer currentAclLevel = getAccessLevel();
                if (currentAclLevel < acl.level()) {
                    throw new RestException("illegal.op");
                }
            }
        }
        return call.proceed();
    }
}
