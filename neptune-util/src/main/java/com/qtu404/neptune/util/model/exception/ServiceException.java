package com.qtu404.neptune.util.model.exception;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/26 下午8:53
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 6608000317844211554L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
