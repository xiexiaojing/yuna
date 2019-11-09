package com.brmayi.yuna.controller;

import com.brmayi.yuna.service.ShowUndeclared;
import com.brmayi.yuna.service.ShowUndeclaredService;
import com.brmayi.yuna.util.ObjProxy;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
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

    @GetMapping("/throwable")
    public String showAllThrowable() {
        return
            "<ul><li>异常和错误基类</li>" +
                "<ul>" +
                    "<li><a href='/errorThrowable'>错误</a></li>" +
                    "<ul>" +
                       "<li>虚拟机错误</li>" +
                       "<ul><li>内部错误</li></ul>" +
                       "<li><a href='/threadDeath'>线程死掉</a></li>" +
                    "</ul>" +
                "</ul>" +
                "<ul>" +
                "   <li><a href='/rethrown'>异常</a></li>" +
                "   <ul>" +
                "     <li>运行时异常</li>" +
                      "<ul>" +
                         "<li><a href='/npe'>空指针异常</a></li>" +
                         "<li><a href='/arithmetic'>算数异常</a></li>" +
                         "<li><a href='/undeclared'>未声明异常</a></li>" +
                         "<li><a href='/illegalArgument'>非法参数异常</a></li>" +
                      "</ul>" +
                    "</ul>"+
                      "<ul>" +
                          "<li><a href='/io'>输入输出异常</a></li>" +
                           "<ul>" +
                              "<li><a href='/socket'>套接字异常</a></li>" +
                                 "<ul>" +
                                   "<li><a href='/binding'>绑定异常</a></li>" +
                                   "<li><a href='/connect'>连接异常</a></li>" +
                                 "</ul>" +
                              "<li><a href='/unknownHost'>主机名未知异常</a></li>" +
                            "</ul>" +
                             "<li><a href='/timeout'>超时异常</a></li>" +
                             "<li>反射操作异常</li>"+
                            "<ul>" +
                               "<li><a href='/classNotFound'>类找不到异常</a></li>" +
                               "<li>被调用目标异常</li>" +
                               "<li>实例化异常</li>" +
                               "<li>非法访问异常</li>" +
                            "</ul>"+
                            "<li>Servlet异常</li>"+
                            "<ul>" +
                                "<li>嵌套Servlet异常</li>" +
                            "</ul>"+
                    "</ul>"+
                "</ul>"+
            "</ul>";
    }

    @GetMapping("/npe")
    public String showNullPointerException() {
        new HashSet<String>(null);
        return prefix + "异常未抛出";
    }

    @GetMapping("/illegalArgument")
    public String showIllegalArgumentException() {
        ShowUndeclared service = new ShowUndeclaredService();
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
        ShowUndeclared service = new ShowUndeclaredService();
        ShowUndeclared serviceProxy = (ShowUndeclared) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(), new ObjProxy(service));
        serviceProxy.showException();
        return prefix + "异常未抛出";
    }

    @GetMapping("/arithmetic")
    public String showArithmeticException() {
        int num = BigDecimal.valueOf(100).divide(BigDecimal.valueOf(11)).intValue();
        return prefix + "异常未抛出, num=" + num;
    }


    @GetMapping("/io")
    public String showIOException() throws Exception {
        Writer writer = new BufferedWriter(new FileWriter("bw.txt"));
        writer.close();
        writer.write("io");
        return prefix + "异常未抛出";
    }

    @GetMapping("/socket")
    public String showSocketException() throws Exception {
        ServerSocket socket = new ServerSocket(8081);
        socket.close();
        socket.setReuseAddress(true);
        return prefix + "异常未抛出";
    }

    @GetMapping("/connect")
    public String showConnectException() throws Exception {
        new Socket("localhost", 8081);
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

    @GetMapping("/errorThrowable")
    public String showErrorThrowable() {
        Error error = new Error("人工抛出一个Error");
        throw error;
    }

    @GetMapping("/threadDeath")
    public String showThreadDeath() {
        Thread.currentThread().stop();
        return prefix + "异常未抛出";
    }

    @GetMapping("/timeout")
    public String showTimeoutException() throws Exception {
        Future<Void> future = CompletableFuture.runAsync(new TimeoutThread());
        future.get(1, TimeUnit.MILLISECONDS);
        return prefix + "异常未抛出";
    }

    @GetMapping("/classNotFound")
    public String showClassNotFoundException() throws Exception {
        Class.forName("com.XXX");
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
