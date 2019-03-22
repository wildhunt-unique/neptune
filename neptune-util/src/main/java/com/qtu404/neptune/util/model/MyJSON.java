package com.qtu404.neptune.util.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:40
 */
@Slf4j
public class MyJSON {
    public static Map<String, Object> toMap(Object o) {
        try {
            String json = JSON.toJSONString(o);
            return JSONObject.parseObject(json);
        } catch (Exception e) {
            log.error("fail.to.transfer.object.to.map.cause:{}", Throwables.getStackTraceAsString(e));
            return Maps.newHashMap();
        }
    }

    public static String toJSON(Object o) {
        try {
            return JSON.toJSONString(o);
        } catch (Exception e) {
            log.error("fail.to.transfer.object.to.json.cause:{}", Throwables.getStackTraceAsString(e));
            return null;
        }
    }

    public static Map<String,Object> jsonStringToMap(String json){
        try {
            return JSONObject.parseObject(json);
        } catch (Exception e) {
            log.error("fail.to.transfer.object.to.json.cause:{}", Throwables.getStackTraceAsString(e));
            return Maps.newHashMap();
        }
    }
}
