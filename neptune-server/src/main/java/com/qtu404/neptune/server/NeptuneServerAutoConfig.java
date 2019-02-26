package com.qtu404.neptune.server;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.qtu404.neptune.util.NeptuneUtilAutoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 上午9:12
 */
@Configuration
@ComponentScan
@EnableDubboConfiguration
@Import(NeptuneUtilAutoConfig.class)
public class NeptuneServerAutoConfig {
}
