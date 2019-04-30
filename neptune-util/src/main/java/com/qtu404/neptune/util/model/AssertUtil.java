package com.qtu404.neptune.util.model;

import com.qtu404.neptune.util.model.exception.AuthorizationException;
import com.qtu404.neptune.util.model.exception.ServiceException;

import java.util.Objects;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/27 下午3:05
 */
public abstract class AssertUtil {
    public static <T> T assertResponseResult(Response<T> response) {
        if (!response.isSuccess()){
            throwExceptionByError(response);
        }
        return response.getResult();
    }

    public static <T> Response<T> assertResponse(Response<T> response) {
        if (!response.isSuccess()){
            throwExceptionByError(response);
        }
        return response;
    }

    public static <T> T sortResponse(Response<T> response) {
        return response.isSuccess() ? response.getResult() : null;
    }

    public static void isExist(Object o, String error) throws ServiceException {
        if (o == null) throw new ServiceException(error + ".not.exist");
    }

    private static void throwExceptionByError(Response response){
        Integer code = response.getCode();
        code = Objects.isNull(code) ? Response.SERVICE_ERROR : code;
        if (code.equals(Response.ARGUMENT_ERROR)) {
            throw new IllegalArgumentException(response.getError());
        } else if (code.equals(Response.SERVICE_ERROR)) {
            throw new ServiceException(response.getError());
        } else if (code.equals(Response.NO_AUTH)) {

            throw new AuthorizationException(response.getError());
        } else {
            throw new ServiceException(response.getError());
        }
    }
}
