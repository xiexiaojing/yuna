package com.brmayi.yuna.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString(callSuper = true)
public class User extends Pojo{
    private Integer id;
    private String name;
    private String password;
    private Integer age;
}
