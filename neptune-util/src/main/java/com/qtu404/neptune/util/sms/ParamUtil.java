package com.qtu404.neptune.util.sms;

import com.qtu404.neptune.util.model.ServiceException;

public class ParamUtil {
    public static void nonNullWithError(Object o, String error) throws IllegalArgumentException {
        if (o == null) throw new IllegalArgumentException(error);
    }

    public static void nonNull(Object o, String error) throws IllegalArgumentException {
        if (o == null) throw new IllegalArgumentException(error + ".not.be.null");
    }

    public static void nonExist(Object o, String error) throws IllegalArgumentException {
        if (o == null) throw new ServiceException(error + ".not.exist");
    }
}
