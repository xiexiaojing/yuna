package com.brmayi.yuna.mybatis;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import javax.xml.crypto.Data;
import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class YunaSession {
   public static Object dealSql(Class clazz) {
       Class c[] = new Class[]{clazz};
       return Proxy.newProxyInstance(YunaSession.class.getClassLoader(), c, new YunaInvocationHandler());
   }
}
