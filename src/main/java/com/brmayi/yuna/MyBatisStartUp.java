package com.brmayi.yuna;

import com.brmayi.yuna.mapper.BookMapper;
import com.brmayi.yuna.mapper.UserMapper;
import com.brmayi.yuna.model.User;
import com.brmayi.yuna.model.Version;
import com.brmayi.yuna.mybatis.YunaSession;
import org.eclipse.jetty.util.security.Credential;

public class MyBatisStartUp {
    public static void main(String[] args) throws Exception{
//        Environment environment = new Environment("test", new ManagedTransactionFactory(), new PooledDataSource("com.mysql.jdbc.Driver","1","1","1"));
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(UserMapper.class);
//        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
//
        UserMapper userMapper = (UserMapper) YunaSession.dealSql(UserMapper.class);

        User user = new User();
        user.setAge(1);
        user.setName("1");
        System.out.println("整个切面完成最终获取到的结果为：" +userMapper.insertUser(user));
        System.out.println("整个切面完成最终获取到的结果为：" +userMapper.selectList());
        System.out.println("整个切面完成最终获取到的结果为：" +userMapper.selectUser());

        BookMapper bookMapper = (BookMapper) YunaSession.dealSql(BookMapper.class);

        System.out.println("整个切面完成最终获取到的结果为：" +bookMapper.insertUser(user));
        System.out.println("整个切面完成最终获取到的结果为：" +bookMapper.selectList());
        System.out.println("整个切面完成最终获取到的结果为：" +bookMapper.selectUser());
        System.out.println(Credential.MD5.digest("122"));
    }
}