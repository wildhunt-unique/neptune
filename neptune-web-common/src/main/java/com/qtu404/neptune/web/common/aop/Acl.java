package com.qtu404.neptune.web.common.aop;

import com.qtu404.neptune.common.constant.AccessLevel;
import io.swagger.models.auth.In;

import java.lang.annotation.*;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/5/4
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Acl {
    int level() default AccessLevel.BASE;
}
