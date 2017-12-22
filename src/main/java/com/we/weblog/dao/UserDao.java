package com.we.weblog.dao;


import com.we.weblog.db.h2.InitDatabase;
import com.we.weblog.domain.User;
import org.h2.result.ResultRemote;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        user.setUserName("admin");
        user.setPassword("admin888");
        insertUser(user);

    }

    /**
     *  根据用户名查找User
     * @param username
     * @return
     */
    public  User searchUserByName(String username) throws Exception {
        User user  = new User();
        if(username == null)  return user;
        else {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                InitDatabase sqlconnect = new InitDatabase();
                //创建连接和statement
                connection = sqlconnect.getConnection();
                statement = connection.createStatement();
                //构建sql语句 然后连接

                String sql = "SELECT * FROM t_user  WHERE username = '" + username + "'";

                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    user.setUserName(username);
                    user.setPassword(resultSet.getString("password"));
                }


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection.close();
                statement.close();
                resultSet.close();
            }
        }
        return user;

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

            String sql = "INSERT INTO t_user (username,password) VALUES ('"
                    + user.getUserName() + "','"
                    + user.getPassword() + "')";

            statement.execute(sql);

        }finally {
            connection.close();
            statement.close();

        }

    }
}
