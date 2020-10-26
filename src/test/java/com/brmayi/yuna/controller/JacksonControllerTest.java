package com.brmayi.yuna.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.brmayi.yuna.model.Pojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JacksonControllerTest {
    @InjectMocks
    private JacksonController codecController;

    @Test
    public void writeStringAsString() throws Exception {
        String result = codecController.writeStringAsString(null);
        System.out.print(result == null);
    }

    @Test
    public void writePojoAsString() throws Exception {
        Pojo pojo = new Pojo();
        pojo.setId(1);
        pojo.setValue("纵情向前");
        String result = codecController.writePojoAsString(pojo);
        System.out.print(result);
    }

    @Test
    public void writeListAsString() throws Exception {
        Pojo pojo = new Pojo();
        pojo.setId(1);
        pojo.setValue("纵情向前");
        String result = "";
        for (int i = 0; i < 10; i++) {
            result = codecController.writeListAsString(pojo);
        }
        if (result.startsWith("[")) {
            try {
                JSONArray o = JSONObject.parseArray(result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.print(result);
    }

    @Test
    public void readPojoValue() throws Exception {
        Pojo pojo = codecController.readPojoValue("{\"id\":1,\"value\":\"纵情向前\"}");
        System.out.print(pojo.getValue());
    }
}