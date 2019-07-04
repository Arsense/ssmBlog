package com.we.weblog.mapper.builder;

import com.we.weblog.domain.User;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Clay
 * @date 2019/7/1 23:58
 */
public class UserSqlBuilder {



    public String buildGetUserQuery(User user) {
        return new SQL(){{
            SELECT("*");
            FROM("hexo_user");
            if (user.getUserName() != null) {
                WHERE("username=#{userName}");
            }
            if (user.getPassword() != null) {
                WHERE("password = #{password}");
            }

            if (user.getUserEmail() != null) {
                WHERE("email = #{userEmail}");
            }

        }}.toString();

    }


    public String buildUpdate( ){
        return "update hexo_user " +
                "set username = #{u.userName}," +
                "password = #{u.password}";
    }


}
