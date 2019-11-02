package com.brmayi.yuna.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(Throwable.class)
    public String handleException(Throwable e) {
        return "ControllerExceptionAdvice消息:" + e.toString();
    }
}
