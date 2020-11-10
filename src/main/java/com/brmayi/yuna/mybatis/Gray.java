package com.brmayi.yuna.mybatis;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Gray {
    String biz();
    String token();
    long cacheInMills() default 0;
}
