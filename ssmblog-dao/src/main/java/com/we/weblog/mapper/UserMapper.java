package com.we.weblog.mapper;


import com.we.weblog.domain.Post;
import com.we.weblog.domain.User;
import com.we.weblog.mapper.builder.UserSqlBuilder;
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
     * @return
     */
    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetUserQuery")
    List<User> queryUser(User user) throws RuntimeException;


    //TODO 修改密码
    @Update({"update hexo_user " +
            "set username = #{u.userName}," +
            "password = #{u.password}"})
    void saveByUser(@Param("u") User user);


    @Update({"update hexo_post " +
            " set title = #{b.title}," +
            " md = #{b.md}," +
            " slug = #{b.slug}," +
            " categories = #{b.categories},"+
            " tags = #{b.tags},"+
            " article=#{b.article} where uid= #{id}"})
    void updatePostByUid(@Param("b") Post context, @Param("id") int uid);






}
