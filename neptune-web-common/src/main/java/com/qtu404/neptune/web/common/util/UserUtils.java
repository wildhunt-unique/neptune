package com.qtu404.neptune.web.common.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qtu404.neptune.api.facade.UserFacade;
import com.qtu404.neptune.common.constant.ConstantValues;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/28 上午10:22
 */
public class UserUtils {

    public static Long getId(HttpSession session) {
        Object value = session.getAttribute(ConstantValues.SESSION_CURRENT_USER_KEY);
        if (Objects.nonNull(value) && value instanceof Long) {
            return (Long) value;
        } else {
            // TODO: 2019/2/28 测试用
            return 100L;
//            return null;
        }
    }

    public static void setId(Long id, HttpSession session) {
        session.setAttribute(ConstantValues.SESSION_CURRENT_USER_KEY, id);
    }
}
