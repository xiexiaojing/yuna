package com.brmayi.yuna.newspring;

import org.springframework.beans.BeansException;

@FunctionalInterface
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}