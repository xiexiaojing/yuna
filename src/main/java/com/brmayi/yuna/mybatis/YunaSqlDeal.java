package com.brmayi.yuna.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.brmayi.yuna.model.Pojo;
import com.brmayi.yuna.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YunaSqlDeal {
    public static int insert(String sql, String param) {
        return 1;
    }

    public static Map<String, JSONObject> gray(String biz) {
        Map<String, JSONObject> jsons = new HashMap<>();
        if("person".equals(biz)) {
            User user1 = new User();
            user1.setId(1);
            user1.setName("贾元春");
            user1.setAge(27);
            jsons.put("1", (JSONObject) JSONObject.toJSON(user1));
            User user2 = new User();
            user2.setId(2);
            user2.setName("贾迎春");
            user2.setAge(26);
            jsons.put("2", (JSONObject) JSONObject.toJSON(user2));
            User user3 = new User();
            user3.setId(3);
            user3.setName("贾探春");
            user3.setAge(25);
            jsons.put("3", (JSONObject) JSONObject.toJSON(user3));
            User user4 = new User();
            user4.setId(4);
            user4.setName("贾惜春");
            user4.setAge(24);
            jsons.put("4", (JSONObject) JSONObject.toJSON(user1));
            System.out.println("根据" + biz + "获取到的灰度中数据如下" + jsons);
        } else {
            Pojo pojo = new Pojo();
            pojo.setValue("红楼梦");
            jsons.put("3", (JSONObject) JSONObject.toJSON(pojo));
        }
        return jsons;
    }

    public static List mockSelectList(String biz) {
        List users = new ArrayList();
        if("person".equals(biz)) {
            User user3 = new User();
            user3.setId(3);
            user3.setName("板儿");
            user3.setAge(14);
            users.add(user3);
            User user4 = new User();
            user4.setId(4);
            user4.setName("巧姐");
            user4.setAge(7);
            users.add(user4);
            User user5 = new User();
            user5.setId(5);
            user5.setName("凤姐");
            user5.setAge(34);
            users.add(user5);
        } else {
            Pojo pojo = new Pojo();
            pojo.setValue("西游记");
            users.add(pojo);
        }
        return users;
    }


    public static Object mockSelect(String biz) {
        if("person".equals(biz)) {
            User user3 = new User();
            user3.setId(3);
            user3.setName("板儿");
            user3.setAge(14);
            return user3;
        } else {
            Pojo pojo = new Pojo();
            pojo.setValue("三国");
            return pojo;
        }
    }
}
