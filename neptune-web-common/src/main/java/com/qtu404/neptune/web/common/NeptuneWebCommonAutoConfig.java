package com.qtu404.neptune.web.common;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 上午11:42
 */
@ComponentScan
@EnableWebMvc
@EnableDubboConfiguration
@Configuration
public class NeptuneWebCommonAutoConfig {
}
