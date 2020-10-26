package com.brmayi.yuna.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.brmayi.yuna.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class YunaInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("con db");
        System.out.println(method.getAnnotatedReturnType().getType().getTypeName());
        if(args!=null) {
            System.out.println(Arrays.toString(args));
        }

        Select select = method.getAnnotation(Select.class);
        if(select != null) {
            String s = select.value()[0];
            System.out.println(s);
            if(method.getAnnotatedReturnType().getType() instanceof ParameterizedType){
                System.out.println("---------------"+method.getAnnotatedReturnType());
                ParameterizedType pt = (ParameterizedType)method.getAnnotatedReturnType().getType();
                Class<?> c = (Class<?>) pt.getActualTypeArguments()[0];
                String json = JSONArray.toJSONString(Lists.newArrayList(new User()));
                System.out.println("+++++++++++++++"+json);
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(json, List.class);
            } else {
                System.out.println("==============="+method.getAnnotatedReturnType());
                Class<?> c = (Class<?>) method.getAnnotatedReturnType().getType();
                String json = JSONArray.toJSONString(new User());
                System.out.println("+++++++++++++++===="+json);
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(json, c);
            }

        }
        Update update = method.getAnnotation(Update.class);
        if(update != null) {
            String s = update.value()[0];
            System.out.println(s);
            return 1;
        }
        Insert insert = method.getAnnotation(Insert.class);
        if(insert != null) {
            String s = insert.value()[0];
            System.out.println(s);
            YunaSqlDeal yunaSqlDeal = new YunaSqlDeal();
            yunaSqlDeal.insert(s, Arrays.toString(args));
            return 1;
        }
        return null;
    }
}
