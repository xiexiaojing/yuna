package com.brmayi.yuna.newspring;

import com.brmayi.yuna.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class YunaApplication {
    public static void main(String[] args) throws Exception {
        ApplicationContext yunaApplicationContext = new AnnotationConfigApplicationContext(YunaConfig.class);
        User user = (User) yunaApplicationContext.getBean("user");
        UserFactoryBean userFactoryBean = (UserFactoryBean) yunaApplicationContext.getBean("&user");
        System.out.println(userFactoryBean);
    }
}