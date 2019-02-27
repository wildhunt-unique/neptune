package com.qtu404.neptune.util.model;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/27 下午3:05
 */
public abstract class AssertUtil {
    public static <T> T assertResponse(Response<T> response) {
        if (response.isSuccess()) {
            return response.getResult();
        } else {
            throw new ServiceException("error");
        }
    }

    public static <T> T sortResponse(Response<T> response) {
        return response.isSuccess() ? response.getResult() : null;
    }
}
