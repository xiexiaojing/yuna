package com.brmayi.yuna.controller;

import java.sql.*;

public class JDBCMySQL {
    public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/mydb";
    //现在使用的是mysql数据库，是直接连接的，所以此处必须有用户名和密码
    public static final String USERNAME = "root";
    public static final String PASSWORD = "mysqladmin";

    public static void main(String[] args) {
        //数据库连接对象
        Connection conn = null;
        //数据库操作对象
        PreparedStatement stmt = null;
        //1、加载驱动程序
        try {
            Class.forName(DBDRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2、连接数据库
        //通过连接管理器连接数据库
        try {
            //在连接的时候直接输入用户名和密码才可以连接
            conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //3、向数据库中插入一条数据
        String sql = "INSERT INTO person(name,age) VALUES (?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"陈昆仑");
            stmt.setInt(2,21);
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //4、执行语句
        try {
            ResultSet resultSet = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5、关闭操作，步骤相反哈~
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
