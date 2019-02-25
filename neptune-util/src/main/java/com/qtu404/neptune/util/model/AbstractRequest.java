package com.qtu404.neptune.util.model;

import java.util.Map;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:40
 */

public abstract class AbstractRequest {
    public void checkParam() {
    }

    public Map<String, Object> toMap() {
        return MyJSON.toMap(this);
    }
}
