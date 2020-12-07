package com.brmayi.yuna.mapper;

import com.brmayi.yuna.model.Pojo;
import com.brmayi.yuna.model.User;
import com.brmayi.yuna.mybatis.DalAndGray;
import com.brmayi.yuna.mybatis.Gray;
import com.brmayi.yuna.mybatis.Dal;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Gray(biz="book")
public interface BookMapper {
    @Insert("INSERT INTO person(name,age) VALUES (#{name},#{age}")
    Integer insertUser(User user);

    @Gray(biz = "1")
    @Select("select * from test where user=1")
    List<Pojo> selectList();


    @Select("select * from test where user=1")
    Pojo selectUser();
}
