package com.qtu404.neptune.web;

import com.qtu404.neptune.web.common.NeptuneWebCommonAutoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:26
 */
@Configuration
@ComponentScan
@Import(NeptuneWebCommonAutoConfig.class)
public class NeptuneWebAutoConfig {
}
