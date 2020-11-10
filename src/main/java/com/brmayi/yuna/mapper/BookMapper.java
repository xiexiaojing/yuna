package com.brmayi.yuna.mapper;

import com.brmayi.yuna.model.Pojo;
import com.brmayi.yuna.model.User;
import com.brmayi.yuna.mybatis.DalAndGray;
import com.brmayi.yuna.mybatis.Gray;
import com.brmayi.yuna.mybatis.Dal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Gray(biz="book", token = "a0a080f42e6f13b3a2df133f073095dd", cacheInMills = 60*1000)
public interface BookMapper {
    @Insert("INSERT INTO person(name,age) VALUES (#{name},#{age}")
    Integer insertUser(User user);

    @Select("select * from test where user=1")
    List<Pojo> selectList();

    @Select("select * from test where user=1")
    Pojo selectUser();
}
