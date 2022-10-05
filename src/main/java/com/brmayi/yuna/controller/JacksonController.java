package com.brmayi.yuna.controller;

import com.brmayi.yuna.model.Pojo;
import com.brmayi.yuna.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JacksonController {
    @Resource
    private User user;

    @GetMapping("/writeStringAsString")
    public String writeStringAsString(String toWrite) throws Exception {
        System.out.println(user.getAge());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(toWrite);
    }

    @GetMapping("/writePojoAsString")
    public String writePojoAsString(@Valid Pojo toWrite) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(toWrite);
    }

    @GetMapping("/writeIntAsString")
    public String writeIntAsString(int toWrite) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(toWrite);
    }

    List<Pojo> pojos = new ArrayList<>();
    @GetMapping("/writeListAsString")
    public String writeListAsString(Pojo toWrite) throws Exception {
        pojos.add(toWrite);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(pojos);
    }


    @GetMapping("/readPojoValue")
    public Pojo readPojoValue(String toRead) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(toRead, Pojo.class);
    }
}
