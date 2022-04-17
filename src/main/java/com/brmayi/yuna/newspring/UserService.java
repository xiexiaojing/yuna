package com.brmayi.yuna.newspring;

import org.springframework.stereotype.Component;

@Component(value = "userService")
public class UserService {

    public void test() {
        System.out.println("UserService Test");
    }
}