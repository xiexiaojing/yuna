package com.brmayi.yuna.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
    @InjectMocks
    private ExceptionController indexControllerMock;

    @Test
    public void showNullPointerException() {
        indexControllerMock.showNullPointerException();
    }

    @Test
    public void showArithmeticException() {
        indexControllerMock.showArithmeticException();
    }

    @Test
    public void showUndeclaredThrowableException() {
        indexControllerMock.showUndeclaredThrowableException();
    }

    @Test
    public void showError() {
        System.out.print(indexControllerMock.showError());
    }
}
