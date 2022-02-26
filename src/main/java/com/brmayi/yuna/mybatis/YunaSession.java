package com.brmayi.yuna.mybatis;

import java.lang.reflect.Proxy;

public class YunaSession {
   public static Object dealSql(Class clazz) {
       Class c[] = new Class[]{clazz};
       return Proxy.newProxyInstance(YunaSession.class.getClassLoader(), c, new YunaInvocationHandler("testBiz"));
   }
}
