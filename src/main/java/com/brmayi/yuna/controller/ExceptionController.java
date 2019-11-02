package com.brmayi.yuna.controller;

import com.brmayi.yuna.service.ShowUndeclaredThrowableException;
import com.brmayi.yuna.service.ShowUndeclaredThrowableExceptionService;
import com.brmayi.yuna.util.ObjProxy;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class ExceptionController {
    private static final String prefix = "ExceptionController消息: ";

    @GetMapping("/exception")
    public String showAllException() {
        return
                "<h3><a href='/npe'>空指针异常</a></h3>" +
                        "<h3><a href='/undeclared'>未声明异常</a></h3>" +
                        "<h3><a href='/arithmetic'>算数异常</a></h3>" +
                        "<h3><a href='/timeout'>超时异常</a></h3>" +
                        "<h3><a href='/illegalArgument'>参数不合法异常</a></h3>" +
                        "<h3><a href='/rethrown'>重新抛出多层异常</a></h3>";
    }

    @GetMapping("/npe")
    public String showNullPointerException() {
        new HashSet<String>(null);
        return prefix + "异常未抛出";
    }

    @GetMapping("/illegalArgument")
    public String showIllegalArgumentException() {
        ShowUndeclaredThrowableException service = new ShowUndeclaredThrowableExceptionService();
        Class<?>[] classes = service.getClass().getInterfaces();
        List<Class> list = Lists.newArrayList();
        for (int i = 0; i <= 65537; i++) {
            list.addAll(Arrays.asList(classes));
        }
        Proxy.newProxyInstance(this.getClass().getClassLoader(),
                list.toArray(new Class<?>[list.size()]), new ObjProxy(this));
        return prefix + "异常未抛出";
    }

    @GetMapping("/undeclared")
    public String showUndeclaredThrowableException() {
        ShowUndeclaredThrowableException service = new ShowUndeclaredThrowableExceptionService();
        ShowUndeclaredThrowableException serviceProxy = (ShowUndeclaredThrowableException) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(), new ObjProxy(service));
        System.out.println("realname=" + serviceProxy.getClass().getName());
        serviceProxy.showException();
        return prefix + "异常未抛出";
    }

    @GetMapping("/arithmetic")
    public String showArithmeticException() {
        int num = (10 / 0);
        return prefix + "异常未抛出, num=" + num;
    }

    @GetMapping("/socket")
    public String showSocketException() throws Exception {
        ServerSocket socket = new ServerSocket(8081);
        socket.close();
        socket.setReuseAddress(true);
        return prefix + "异常未抛出";
    }

    @GetMapping("/binding")
    public String showBindingException() throws Exception {
        ServerSocket socket = new ServerSocket(80);
        socket.setReuseAddress(true);
        return prefix + "异常未抛出";
    }

    @GetMapping("/unknownHost")
    public String showUnknownHostException() throws Exception {
        new Socket("ttt", 5300);
        return prefix + "异常未抛出";
    }

    @GetMapping("/error")
    public String showError() {
        Error error = new Error("人工抛出一个Error");
        throw error;
    }

    @GetMapping("/timeout")
    public String showTimeoutException() throws Exception {
        Future<Void> future = CompletableFuture.runAsync(new TimeoutThread());
        future.get(1, TimeUnit.MILLISECONDS);
        return prefix + "异常未抛出";
    }

    @GetMapping("/rethrown")
    public String showRethrownException() {
        try {
            try {
                throw new TimeoutException();
            } catch (Exception e) {
                throw new IOException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private class TimeoutThread implements Runnable {

        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
