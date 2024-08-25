package com.brmayi.yuna.mybatis;

import org.springframework.beans.BeansException;

@FunctionalInterface
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}