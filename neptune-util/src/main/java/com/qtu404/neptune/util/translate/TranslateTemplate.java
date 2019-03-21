package com.qtu404.neptune.util.translate;

import com.qtu404.neptune.util.model.Response;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/21 上午10:58
 */
public interface TranslateTemplate {
    Response<String> doTranslate(TranslateRequest request);
}
