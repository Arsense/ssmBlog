package com.we.weblog.mapper;


import com.we.weblog.domain.Post;
import com.we.weblog.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *    @TODO 用户数据Mapper 处理用户的登陆什么的
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     * 根据用户名搜索User
     * @param username
     * @return
     */
    @Select({"select * from t_user where username = #{name} and password = #{pass}"})
    User selectByPassAndName(@Param("name") String username,@Param("pass") String password) throws RuntimeException;

    @Select({"select * from t_user where userEmail = #{email} and password = #{pass}"})
    User findByUserEmailAndPassword(@Param("email") String userEmail,@Param("pass") String password) throws RuntimeException;


    //TODO 修改密码
    @Update({" update t_user" +
            "set username = #{u.username}," +
            "password = #{u.password}"})
    void saveByUser(@Param("u") User user);




    @Update({" update t_context " +
            " set title = #{b.title}," +
            " md = #{b.md}," +
            " slug = #{b.slug}," +
            " categories = #{b.categories},"+
            " tags = #{b.tags},"+
            " article=#{b.article} where uid= #{id}"})
    void updatePostByUid(@Param("b") Post context, @Param("id") int uid);

    @Select({"select user from t_user where username=#{name} and password=#{ps}"})
    User findByUserIdAndPassword(@Param("name") String username,@Param("ps") String password);


    /**
     * 查询所有User
     * @param
     * @return
     */
    @Select({"select * from t_user"})
    List<User> findAllUsers() throws RuntimeException;


}
