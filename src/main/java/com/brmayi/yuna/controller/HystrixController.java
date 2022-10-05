package com.brmayi.yuna.controller;

import com.brmayi.yuna.util.DemoHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/hystrix")
    public String hystrix(String poolName) throws Exception {
        DemoHystrixCommand demoHystrixCommand = applicationContext.getBean(DemoHystrixCommand.class);
        demoHystrixCommand.setPoolName(poolName);
        return demoHystrixCommand.execute();
    }
}
