package com.qtu404.neptune.util.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:42
 */
@Data
@ApiModel("返回结果")
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 5723344170079564065L;
    @ApiModelProperty("是否成功")
    private boolean success;

    @ApiModelProperty("结果")
    private T result;

    @ApiModelProperty("错误信息")
    private String error;

    @ApiModelProperty("返回码")
    private Integer code;

    private static final Integer SUCCESS_CODE = 200;
    public static final Integer SERVICE_ERROR = 500;
    public static final Integer ARGUMENT_ERROR = 400;

    public static <T> Response<T> success(T t) {
        Response<T> response = new Response<>();
        response.setCode(SUCCESS_CODE);
        response.setResult(t);
        response.setSuccess(true);
        return response;
    }

    public static <T> Response<T> fail(String error) {
        Response<T> resp = new Response<>();
        resp.setError(error);
        resp.setCode(SERVICE_ERROR);
        return resp;
    }

    public static <T> Response<T> fail(String error, Integer code) {
        Response<T> resp = new Response<>();
        resp.setError(error);
        resp.setCode(code);
        return resp;
    }
}
