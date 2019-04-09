package com.qtu404.neptune.server.starter;

import com.qtu404.neptune.server.NeptuneServerAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.concurrent.CountDownLatch;

/*

                        _ooOoo_
                       o8888888o
                       88" . "88
                       (| -_- |)
                        O\ = /O
                    ____/`---'\____
                  .   ' \\| |// `.
                   / \\||| : |||// \
                 / _||||| -:- |||||- \
                   | | \\\ - /// | |
                 | \_| ''\---/'' | |
                  \ .-\__ `-` ___/-. /
               ___`. .' /--.--\ `. . __
            ."" '< `.___\_<|>_/___.' >'"".
           | | : `- \`.;`\ _ /`;.`/ - ` : | |
             \ \ `-. \_ __\ /__ _/ .-` / /
     ======`-.____`-.___\_____/___.-`____.-'======
                        `=---='

     .............................................
              佛祖保佑                永无BUG

                   江城子 . 程序员之歌

               十年生死两茫茫，写程序，到天亮。
                   千行代码，Bug何处藏。
              纵使上线又怎样，朝令改，夕断肠。

                需求每天新想法，天天改，日日忙。
                   相顾无言，惟有泪千行。
               每晚灯火阑珊处，夜难寐，加班狂。
 */

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
