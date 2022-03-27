package com.brmayi.yuna.newspring;

public class YunaApplication {
    public static void main(String[] args) throws Exception {
        YunaApplicationContext yunaApplicationContext = new YunaApplicationContext(YunaConfig.class);
        UserService userService= (UserService)yunaApplicationContext.getBean("userService");
        userService.test();
    }
}