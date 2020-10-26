package com.brmayi.yuna;

import com.brmayi.yuna.mapper.UserMapper;
import com.brmayi.yuna.model.User;
import com.brmayi.yuna.mybatis.YunaConnection;
import com.brmayi.yuna.mybatis.YunaSession;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.*;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.datasource.ConnectionProxy;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Map;
import java.util.Properties;
import org.apache.ibatis.executor.BaseExecutor;
import java.util.logging.Logger;

public class MyBatisStartUp {
    public static void main(String[] args) throws Exception{
//        Environment environment = new Environment("test", new ManagedTransactionFactory(), new PooledDataSource("com.mysql.jdbc.Driver","1","1","1"));
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(UserMapper.class);
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);

        UserMapper userMapper = (UserMapper) YunaSession.dealSql(UserMapper.class);

        User user = new User();
        user.setAge(1);
        user.setName("1");
        System.out.println(userMapper.insertUser(user));

        System.out.println(userMapper.selectList());
        System.out.println(userMapper.selectUser());
    }
}