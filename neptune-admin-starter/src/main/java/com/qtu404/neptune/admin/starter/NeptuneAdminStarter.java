package com.qtu404.neptune.admin.starter;

import com.qtu404.neptune.admin.NeptuneAdminAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 上午11:50
 */
@SpringBootApplication
@Import(NeptuneAdminAutoConfig.class)
public class NeptuneAdminStarter {
    public static void main(String[] args) {
        SpringApplication.run(NeptuneAdminStarter.class);
    }
}
