package com.brmayi.yuna.mybatis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class YunaInvocationHandlerBak implements InvocationHandler {
    String biz = null;
    public YunaInvocationHandlerBak(String biz) {
        this.biz = biz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("con db");
        System.out.println(method.getAnnotatedReturnType().getType().getTypeName());
        if (args != null) {
            System.out.println(Arrays.toString(args));
        }

        Select select = method.getAnnotation(Select.class);
        if (select != null) {
            String s = select.value()[0];
            System.out.println("获取到的查询SQL语句为"+s);
            if (method.getAnnotatedReturnType().getType() instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) method.getAnnotatedReturnType().getType();
                Class<?> c = (Class<?>) pt.getActualTypeArguments()[0];
                String json = JSONArray.toJSONString(YunaSqlDeal.mockSelectList(biz));
                ObjectMapper objectMapper = new ObjectMapper();
                result = objectMapper.readValue(json, List.class);
                System.out.println("从盘古中获取的原数据列表为" + json);
            } else {
                Class<?> c = (Class<?>) method.getAnnotatedReturnType().getType();
                String json = JSONArray.toJSONString(YunaSqlDeal.mockSelect(biz));
                ObjectMapper objectMapper = new ObjectMapper();
                result = objectMapper.readValue(json, c);
                System.out.println("从盘古中获取的原数据为" + json);
            }

        }
        Update update = method.getAnnotation(Update.class);
        if (update != null) {
            String s = update.value()[0];
            System.out.println("更新语句为"+s);
            return 1;
        }
        Insert insert = method.getAnnotation(Insert.class);
        if (insert != null) {
            String s = insert.value()[0];
            System.out.println("插入语句为"+s);
            YunaSqlDeal yunaSqlDeal = new YunaSqlDeal();
            yunaSqlDeal.insert(s, Arrays.toString(args));
            return 1;
        }
        Gray gray = method.getAnnotation(Gray.class);
        if (gray != null && result !=null) {
            if(StringUtils.isNotEmpty(gray.biz())) {
                biz = gray.biz();
            }
            Map<String, JSONObject> jsons = YunaSqlDeal.gray(biz);
            if (method.getAnnotatedReturnType().getType() instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) method.getAnnotatedReturnType().getType();
                Class<?> c = (Class<?>) pt.getActualTypeArguments()[0];
                List results = (List)result;
                List newResults = Lists.newArrayList();
                for(Object o : results) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String json = objectMapper.writeValueAsString(o);
                    JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
                    String id = jsonObject.getString("id");
                    JSONObject newObject = jsons.get(id);
                    if(newObject!=null) {
                        newResults.add(objectMapper.readValue(newObject.toJSONString(), c));
                    } else {
                        newResults.add(objectMapper.readValue(jsonObject.toJSONString(), c));
                    }
                }
                result = newResults;
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(result);
                JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
                String id = jsonObject.getString("id");
                JSONObject newObject = jsons.get(id);
                if(newObject!=null) {
                    Class<?> c = (Class<?>) method.getAnnotatedReturnType().getType();
                    result = objectMapper.readValue(newObject.toJSONString(), c);
                }
            }
        }
        return result;
    }
}
