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
        try {
            System.out.println("运行开始");
            System.out.println(indexControllerMock.showNullPointerException());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出异常结束");
        }
        System.out.println("方法运行结束");
    }

    @Test
    public void showIOException() {
        try {
            System.out.println(indexControllerMock.showIOException());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showArithmeticException() {
        try {
            indexControllerMock.showArithmeticException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showUndeclaredThrowableException() {
        try {
            indexControllerMock.showUndeclaredThrowableException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showConnectException() {
        try {
            indexControllerMock.showConnectException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showError() {
        try {
            System.out.print(indexControllerMock.showErrorThrowable());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Test
    public void showThreadDeath() {
        try {
            System.out.print(indexControllerMock.showThreadDeath());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
