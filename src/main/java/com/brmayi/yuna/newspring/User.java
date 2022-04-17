package com.brmayi.yuna.newspring;

import com.brmayi.yuna.model.Pojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class User extends Pojo{
    private Integer id;
    private String name;
    private String password;
    private Integer age;
}
