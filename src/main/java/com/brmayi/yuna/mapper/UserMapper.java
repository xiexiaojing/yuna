package com.brmayi.yuna.mapper;

import com.brmayi.yuna.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Insert("insert into test (id,name)values(#{id},#{name})")
    Integer insertUser(User user);

    @Select("select * from test where user=1")
    List<User> selectList();

    @Select("select * from test where user=1")
    User selectUser();
}
