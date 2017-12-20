package com.we.weblog.db.h2;


import  org.h2.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class InitDatabase {


    static String databaseName="test";
    static String url= String.format("jdbc:h2:mem:%s;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false",databaseName);
    public static Driver instance = null;

    public static InitDatabase initDatabase;
    // static{}语句块中的内容只被执行一次
    static{

            instance = Driver.load();

     //连接驱动 构建连接
     try {
            java.sql.Driver d= DriverManager.getDriver(url);
            //创建自身类初始化
            initDatabase = new InitDatabase();
            initDatabase.init();
     } catch (SQLException e) {
            e.printStackTrace();
     } catch (Exception e) {
         e.printStackTrace();
     }


    }

    /**
     * 初始化数据库
     * @throws SQLException
     */
    public void init() throws Exception {

        //建立连接
        Connection connection = getConnection();
        //用于执行静态SQL语句的对象
        Statement statement = connection.createStatement();
        createTable(statement);
        //创建完毕关闭数据库
        connection.close();


    }

    public static Connection getConnection() throws Exception{
        Properties prop = new Properties();
        prop.setProperty("user", "sa");
        prop.setProperty("password", "sa");
        return instance.connect(url, prop);
    }

    /**
     *  创建数据库的表
     * @param statement
     * @return
     * @throws SQLException
     */
    public void createTable(Statement statement) throws SQLException {
        //创建用户表
//        statement.execute("CREATE TABLE `t_blog` (\n" +
//                "  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '博客类型',\n" +
//                "  `title` varchar(200) NOT NULL COMMENT '博客题目',\n" +
//                "  `summary` varchar(400) DEFAULT NULL COMMENT '博客摘要',\n" +
//                "  `releaseDate` datetime DEFAULT NULL COMMENT '发布日期',\n" +
//                "  `clickHit` int(11) DEFAULT NULL COMMENT '评论次数',\n" +
//                "  `replyHit` int(11) DEFAULT NULL COMMENT '回复次数',\n" +
//                "  `content` text COMMENT '博客内容',\n" +
//                "  `keyWord` varchar(200) DEFAULT NULL COMMENT '关键字',\n" +
//                "  `type_id` int(11) DEFAULT NULL COMMENT '外键关联博客类别',\n" +
//                "  PRIMARY KEY (`id`),\n" +
//                "  KEY `type_id` (`type_id`),\n" +
//                "  CONSTRAINT `t_blog_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `t_blogtype` (`id`)\n" +
//                ") ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8");

        //创建登录框
        statement.execute("CREATE TABLE `t_user` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',\n" +
                "  `username` varchar(30) NOT NULL COMMENT '用户姓名',\n" +
                "  `password` varchar(400) DEFAULT NULL COMMENT '用户密码',\n" +
                "  PRIMARY KEY (`id`),\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8");


    }


    public static void main(String[] args) throws SQLException {
        try {
            Connection connection = getConnection();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


}
