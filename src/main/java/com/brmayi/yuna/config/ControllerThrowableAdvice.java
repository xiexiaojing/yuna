package com.brmayi.yuna.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerThrowableAdvice {

    @ExceptionHandler(Throwable.class)
    public String handleThrowable(Throwable e) {
        return "ControllerThrowableAdvice消息:" + e.toString();
    }
}
