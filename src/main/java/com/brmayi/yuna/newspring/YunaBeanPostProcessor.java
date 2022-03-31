package com.brmayi.yuna.newspring;

import org.springframework.beans.BeansException;

@Component
public class YunaBeanPostProcessor implements BeanPostProcessor{

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("我知道我是任性很任性，伤透了你的心");
        return bean;
    }


    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("我知道你是任性很任性，伤透了我的心");
        return bean;
    }
}