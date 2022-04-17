package com.brmayi.yuna.mapper;

import com.brmayi.yuna.model.User;
import com.brmayi.yuna.newspring.Component;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Component(value = "userMapper")
public interface UserMapper {
    @Insert("INSERT INTO person(name,age) VALUES (#{name},#{age}")
    Integer insertUser(User user);

    @Select("select * from test where user=1")
    List<User> selectList();

    @Select("select * from test where user=1")
    User selectUser();
}
