package com.qtu404.neptune.util.translate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/3/21 上午10:57
 */
@Component
public class TranslateHandler {
    private final TranslateTemplate translateTemplate;

    @Autowired
    public TranslateHandler(TranslateTemplate translateTemplate) {
        this.translateTemplate = translateTemplate;
    }
}
