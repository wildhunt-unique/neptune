package com.qtu404.neptune.util.sms;

public class ParamUtil {
    public static void nonNullWithError(Object o, String error) throws IllegalArgumentException {
        if (o == null) throw new IllegalArgumentException(error);
    }

    public static void nonNull(Object o, String error) throws IllegalArgumentException {
        if (o == null) throw new IllegalArgumentException(error + ".can.not.be.null");
    }
}
