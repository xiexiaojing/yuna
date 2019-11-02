package com.brmayi.yuna.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ObjProxy implements InvocationHandler {
    private Object target;

    public ObjProxy(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}
