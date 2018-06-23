package com.zhuxl.exportdoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * <p>
 *
 * @packageName: com.zhuxl.exportdoc
 * @className:
 * @description:
 * @author: 2810010108@qq.com
 * @createTime: 2018/6/23 14:03 with idea 2018.1
 * </p>
 * version: V1.0.0
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
