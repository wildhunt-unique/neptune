package com.qtu404.neptune.util.model.exception;

/**
 * @author DingXing wildhunt_geralt@foxmail.com
 * @date 2019/4/22
 */
public class RestException  extends RuntimeException {

    private static final long serialVersionUID = -2702211438037543600L;

    public RestException() {
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(Throwable cause) {
        super(cause);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }
}