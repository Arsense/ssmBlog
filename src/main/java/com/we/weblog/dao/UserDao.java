package com.we.weblog.dao;


import com.we.weblog.domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class UserDao {
//    //初始化一次数据
//    private static int judge = 0;
//
//
//    public void insertUser(User user) throws Exception {
//
//        Connection connection = null;
//        Statement statement = null;
//        try {
//            InitDatabase sqlconnect = new InitDatabase();
//            //创建连接和statement
//            connection = sqlconnect.getConnection();
//            statement = connection.createStatement();
//            //构建sql语句 然后连接
//
//            String sql = "INSERT INTO t_user (username,password) VALUES ('"
//                    + user.getUserName() + "','"
//                    + user.getPassword() + "')";
//
//            statement.execute(sql);
//
//        }finally {
//            connection.close();
//            statement.close();
//
//        }
//
//    }
}
