package com.qtu404.neptune.server.starter;

import com.qtu404.neptune.server.NeptuneServerAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.concurrent.CountDownLatch;

/**
 * @author DingXing wb-dx470808@alibaba-inc.com
 * @date 2019/2/25 上午9:20
 */
@SpringBootApplication
@Import(NeptuneServerAutoConfig.class)
public class NeptuneServerStarter {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(NeptuneServerStarter.class,args);
        new CountDownLatch(1).await();
    }
}
