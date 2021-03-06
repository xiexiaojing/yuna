package com.brmayi.yuna.mapper;

import com.brmayi.yuna.model.User;
import com.brmayi.yuna.mybatis.Gray;
import com.brmayi.yuna.mybatis.Dal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface UserMapper {
    @Insert("INSERT INTO person(name,age) VALUES (#{name},#{age}")
    Integer insertUser(User user);

    @Select("select * from test where user=1")
    List<User> selectList();

    @Select("select * from test where user=1")
    User selectUser();
}
