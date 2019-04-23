package com.qtu404.neptune.util.sms;

import com.qtu404.neptune.util.model.exception.ServiceException;

import java.util.Collection;

public class ParamUtil {
    public static void nonNull(Object o, String error) throws IllegalArgumentException {
        if (o == null) throw new IllegalArgumentException(error + ".not.be.null");
    }

    public static void nonExist(Object o, String error) throws IllegalArgumentException {
        if (o == null) throw new ServiceException(error + ".not.exist");
    }

    public static void nonEmpty(Collection collection, String error) {
        if (collection == null || collection.size() == 0) throw new ServiceException(error + ".not.be.empty");
    }
}
