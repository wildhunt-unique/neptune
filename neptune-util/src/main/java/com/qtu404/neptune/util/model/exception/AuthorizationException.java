package com.qtu404.neptune.util.model.exception;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/22
 */
public class AuthorizationException  extends RuntimeException {

    private static final long serialVersionUID = -2930146209344142410L;

    public AuthorizationException() {
    }

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(Throwable cause) {
        super(cause);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}