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
            SELECT("select *");
            FROM("hexo_user");
            if (user.getUserName() != null) {
                WHERE("username=#{username}");
            }
            if (user.getPassword() != null) {
                WHERE("password = #{pass}");
            }

            if (user.getUserEmail() != null) {
                WHERE("userEmail = #{email}");
            }

        }}.toString();

    }


    public String buildUpdate( ){
        return "update hexo_user " +
                "set username = #{u.userName}," +
                "password = #{u.password}";
    }


}
