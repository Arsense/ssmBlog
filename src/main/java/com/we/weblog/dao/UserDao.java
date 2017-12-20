package com.we.weblog.dao;


import com.we.weblog.db.h2.InitDatabase;
import com.we.weblog.domain.User;

import java.sql.Connection;
import java.sql.Statement;

public class UserDao {
    //初始化一次数据
    private static int judge = 0;

    public UserDao() throws Exception {
        if(judge == 0) {
            createAdmin();  //初始化测试的数据
        }
        judge++;
    }

    public void createAdmin() throws Exception {
        User user = new User();
        user.setUserName("clay");
        user.setPassword("admin888");
        insertUser(user);

    }


    public void insertUser(User user) throws Exception {

        Connection connection = null;
        Statement statement = null;
        try {
            InitDatabase sqlconnect = new InitDatabase();
            //创建连接和statement
            connection = sqlconnect.getConnection();

            statement = connection.createStatement();
            //构建sql语句 然后连接

            String sql = "INSERT INTO t_user (user_name,user_password) VALUES ('"
                    + user.getUserName() + "','"
                    + user.getPassword() + "')";

            statement.execute(sql);

        }finally {
            connection.close();
            statement.close();

        }

    }
}
