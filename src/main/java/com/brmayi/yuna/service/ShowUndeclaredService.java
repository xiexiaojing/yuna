package com.brmayi.yuna.service;

public class ShowUndeclaredService implements ShowUndeclared {
    public void showException() {
        System.out.print(10 / 0);
    }
}
