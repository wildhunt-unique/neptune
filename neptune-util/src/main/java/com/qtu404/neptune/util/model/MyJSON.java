package com.qtu404.neptune.util.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:40
 */
public class MyJSON {
    public static Map<String, Object> toMap(Object o) {
        String json = JSON.toJSONString(o);
        return JSONObject.parseObject(json);
    }

    public static String toJSON(Object o) {
        return JSON.toJSONString(o);
    }
}
