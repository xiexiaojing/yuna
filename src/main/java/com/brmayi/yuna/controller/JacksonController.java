package com.brmayi.yuna.controller;

import com.brmayi.yuna.model.Pojo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class JacksonController {

    @GetMapping("/writeStringAsString")
    public String writeStringAsString(String toWrite) throws Exception {
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

    List<Pojo> pojos = Lists.newArrayList();
    @GetMapping("/writeListAsString")
    public String writeListAsString(Pojo toWrite) throws Exception {
        pojos.add(toWrite);
        ObjectMapper objectMapper = new ObjectMapper();
        String r = objectMapper.writeValueAsString(pojos);
        return r;
    }


    @GetMapping("/readPojoValue")
    public Pojo readPojoValue(String toRead) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(toRead, Pojo.class);
    }
}
