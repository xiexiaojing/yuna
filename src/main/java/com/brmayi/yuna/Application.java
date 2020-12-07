package com.brmayi.yuna;

import com.brmayi.yuna.model.User;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;

import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = "com.brmayi.yuna", exclude = {DataSourceAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(1);
        user.setName("1");
        Integer i = null;
        System.out.println(Math.min(i, 1));
    }
}