package com.qtu404.neptune.web.starter;

import com.qtu404.neptune.web.NeptuneWebAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 下午1:29
 */
@SpringBootApplication
@Import(NeptuneWebAutoConfig.class)
public class NeptuneWebStarter {
    public static void main(String[] args) {
        SpringApplication.run(NeptuneWebStarter.class);
    }
}
