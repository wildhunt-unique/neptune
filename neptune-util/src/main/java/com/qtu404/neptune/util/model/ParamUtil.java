package com.qtu404.neptune.util.model;

import com.qtu404.neptune.util.model.exception.ServiceException;

import java.util.Collection;
import java.util.regex.Pattern;

public class ParamUtil {
    private static final String PHONE_NUMBER_REGEX = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";

    public static void nonNull(Object o, String error) throws IllegalArgumentException {
        if (o == null) throw new IllegalArgumentException(error + ".not.be.null");
    }

    public static void nonExist(Object o, String error) throws IllegalArgumentException {
        if (o == null) throw new ServiceException(error + ".not.exist");
    }

    public static void nonEmpty(Collection collection, String error) {
        if (collection == null || collection.size() == 0) throw new ServiceException(error + ".not.be.empty");
    }

    public static void isPhoneNumber(String mobile) {
        if (mobile == null) {
            throw new IllegalArgumentException("mobile.error");
        }else if (mobile.length() != 11) {
            throw new IllegalArgumentException("mobile.error");
        } else if (!Pattern.compile(PHONE_NUMBER_REGEX).matcher(mobile).matches()) {
            throw new IllegalArgumentException("mobile.error");
        }
    }
}
