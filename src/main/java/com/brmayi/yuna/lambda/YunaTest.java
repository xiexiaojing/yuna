package com.brmayi.yuna.lambda;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class YunaTest {
    public YunaTest(YunaInterface yunaInterface) {
        yunaInterface.test();
    }
//
//    public static void main(String[] args) {
//        YunaInterface yunaInterface = () -> System.out.println("yuna");
//        System.out.println(yunaInterface.getClass());
//        System.out.println(yunaInterface.getClass().getSuperclass());
//        System.out.println(Arrays.toString(yunaInterface.getClass().getInterfaces()));
//    }

    public static void main(String[] args) {
        Lists.newArrayList("yuna")
                .forEach(((Consumer<Object>) b -> System.out.println("第一遍" + b))
                        .andThen(b -> System.out.println("第二遍" + b)));
    }
}
