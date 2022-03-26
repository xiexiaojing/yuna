package com.brmayi.yuna.newspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class YunaApplication {
    public static void main(String[] args) throws Exception {
        YunaApplicationContext yunaApplicationContext = new YunaApplicationContext(YunaConfig.class);
        UserService userService= (UserService)yunaApplicationContext.getBean("userService");
        userService.test();
    }
}