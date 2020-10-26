//package com.brmayi.yuna.service;
//
//import com.brmayi.yuna.mapper.UserMapper;
//import com.brmayi.yuna.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl {
//    /*
//     *     问题1 ： UserMapper是接口，如何创建对象？
//     *     答： 使用MyBatis的SqlSession 会话对象。
//     *
//     *    问题2：SqlSession如何创建？
//     *    答： 使用MyBatis的SqlSessionFactory 工厂对象创建
//     *
//     *    问题3:SqlSessionFactory工厂对象如何创建？
//     *
//     *    答：在使用Spring之前
//     *    开发者自己写代码读取MyBatis配置文件，创建SqlSessionFactory
//     *    使用Spring之后，让Spring框架帮我们创建
//     *
//     *    问题4：SqlSessionFactory 工厂对象Spring框架如何创建出来？
//     *
//     *    答：MyBatis工厂对象创建的类在 MyBatis框架和Spring集成的桥梁包mybatis-spring-1.3.1.jar
//     *      的org.mybatis.spring.SqlSessionFactoryBean 负责创建工厂对象
//     *        开发者只需要在Spring配置配置此类就可以创建出来SqlSessionFactory对象
//     *
//     */
//    @Autowired
//    private UserMapper mapper;
//
//    public int insert(User user) {
//        return mapper.insert(user);
//    }
//
//    public int deleteByPrimaryKey(Integer id) {
//        return mapper.deleteByPrimaryKey(id);
//    }
//
//    public int updateByPrimaryKey(User user) {
//        return mapper.updateByPrimaryKey(user);
//    }
//
//    public User selectByPrimaryKey(Integer id) {
//        return mapper.selectByPrimaryKey(id);
//    }
//
//    public List<User> selectList() {
//        return mapper.selectList();
//    }
//}