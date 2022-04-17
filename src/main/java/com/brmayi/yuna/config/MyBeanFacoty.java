package com.brmayi.yuna.config;

import com.brmayi.yuna.model.User;
import com.brmayi.yuna.model.Version;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFacoty implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        User user1 = new User();
        user1.setId(1);
        user1.setName("贾元春");
        user1.setAge(27);
        configurableListableBeanFactory.registerSingleton("user", user1);
    }
}