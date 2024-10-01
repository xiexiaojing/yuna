package com.brmayi.yuna;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.brmayi.yuna.ai.aiOps.infrastructure.mapper")
@EnableScheduling
public class Application {
    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(Application.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }
}