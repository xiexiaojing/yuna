package com.brmayi.yuna.controller;

import com.brmayi.yuna.model.Pojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JacksonController {

    @GetMapping("/writeStringAsString")
    public String writeStringAsString(String toWrite) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(toWrite);
    }

    @GetMapping("/writePojoAsString")
    public String writePojoAsString(Pojo toWrite) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(toWrite);
    }

    @GetMapping("/readPojoValue")
    public Pojo readPojoValue(String toRead) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(toRead, Pojo.class);
    }
}
