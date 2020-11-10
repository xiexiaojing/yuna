package com.brmayi.yuna.mybatis;

import com.brmayi.yuna.model.User;
import org.apache.ibatis.annotations.Insert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YunaInvocationHandler implements InvocationHandler {
    public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/mydb";
    //现在使用的是mysql数据库，是直接连接的，所以此处必须有用户名和密码
    public static final String USERNAME = "root";
    public static final String PASSWORD = "mysqladmin";

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception{
        Object result = null;
        Insert insert = method.getAnnotation(Insert.class);
        if (insert != null) {
            String sql = insert.value()[0];
            System.out.println("插入语句为"+s);
            YunaSqlDeal yunaSqlDeal = new YunaSqlDeal();
            yunaSqlDeal.insert(sql, Arrays.toString(args));

            //1、加载驱动程序
            try {
                Class.forName(DBDRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //2、连接数据库
            //通过连接管理器连接数据库
            //数据库连接对象
            Connection conn = null;
            try {
                //在连接的时候直接输入用户名和密码才可以连接
                conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            composeStatement(sql, args[0], conn);
        }
        return 1;
    }
    private static final String PATTERN = "#\\{[A-Za-z0-9]+\\}";
    private static Pattern pattern = Pattern.compile("("+PATTERN+")");

    public static void composeStatement(String sql, Object obj, Connection conn) throws Exception{
        PreparedStatement stmt = conn.prepareStatement(sql.replaceAll(PATTERN, ""));
        Matcher m= pattern.matcher(sql);
        int i=1;
        while(m.find()) {
            System.out.println(m.group());
            String group = m.group();
            String fieldName = group.replace("#{","").replace("}","");
            Field field = User.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            if("java.lang.Integer".equals(field.getType().getName())) {
                System.out.println("stmt.setInt("+i+","+field.get(obj)+")");
                stmt.setInt(i, Integer.parseInt(field.get(obj).toString()));
            } else if("java.lang.String".equals(field.getType().getName())) {
                stmt.setString(i, field.get(obj).toString());
            }
            i++;
        }
        stmt.execute();
        stmt.close();
        conn.close();
    }
}
