package com.brmayi.yuna.newspring;


import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JettyConfig {
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory()
    {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        factory.setPort(9000);

        // Tweak the connection config used by Jetty to handle incoming HTTP
        // connections
        final QueuedThreadPool threadPool = new QueuedThreadPool();
        //默认最大线程连接数200
        threadPool.setMaxThreads(5000);
        //默认最小线程连接数8
        threadPool.setMinThreads(20);
        //默认线程最大空闲时间60000ms
        threadPool.setIdleTimeout(60000);
        factory.setThreadPool(threadPool);
        return factory;
    }
}