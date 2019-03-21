package com.qtu404.neptune.web.common.aop;

import com.qtu404.neptune.util.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
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
public class TranslateHandler {
    private static Properties prop = new Properties();

    static {
        try {
            InputStream input = TranslateHandler.class.getClassLoader().getResourceAsStream(("message/message_zh.properties"));
            prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object translateError(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed(joinPoint.getArgs());
        if (result instanceof Response) {
            Response response = (Response) result;
            // 如果失败了，对error进行翻译
            if (!response.isSuccess()) {
                translateError(response);
            }
        }
        return result;
    }

    /**
     * 翻译错误
     *
     * @param response 结果信息
     */
    private void translateError(Response response) {
        if (Objects.nonNull(prop)) {
            // 去翻译文件里找
            String error = prop.getProperty(response.getError());
            if (!StringUtils.isEmpty(error)) {
                response.setError(error);
            }
        }
    }


}
