package com.brmayi.yuna.newspring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("user")
public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User(){
          //随便实现
        };
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
