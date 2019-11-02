package com.brmayi.yuna.service;

public class ShowUndeclaredThrowableExceptionService implements ShowUndeclaredThrowableException {
    public void showException() {
        System.out.print(10 / 0);
    }
}
