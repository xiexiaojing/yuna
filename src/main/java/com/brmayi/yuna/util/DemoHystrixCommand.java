package com.brmayi.yuna.util;

import com.netflix.hystrix.*;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DemoHystrixCommand extends HystrixCommand<String> {
    private static final Logger logger = LoggerFactory.getLogger(DemoHystrixCommand.class);
    private String poolName;

    public DemoHystrixCommand() {
        super(Setter.withGroupKey(
                //服务分组
                HystrixCommandGroupKey.Factory.asKey("DemoGroup"))
                //线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("DemoPool"))
                //线程池配置
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                                .withCoreSize(2)
                                .withKeepAliveTimeMinutes(5)
                                .withMaxQueueSize(2)
                                .withQueueSizeRejectionThreshold(10))
                //线程池隔离
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                )
        );
    }

    @Override
    protected String run() throws Exception {
        logger.info(poolName + ":我伤心我无奈,可是我默默等待");
        TimeUnit.MILLISECONDS.sleep(100);
        return poolName + "-run:缘分就是一生的等待";
    }

    protected String getFallback() {
        return  poolName + "-fallback:同是追梦的人，难舍难分";
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }
}