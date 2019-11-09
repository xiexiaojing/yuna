package com.brmayi.yuna.controller;

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
        System.out.print(result==null);
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
    public void readPojoValue() throws Exception {
        Pojo pojo = codecController.readPojoValue("{\"id\":1,\"value\":\"纵情向前\"}");
        System.out.print(pojo.getValue());
    }
}