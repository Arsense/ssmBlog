package com.we.weblog.mapper;


import com.we.weblog.domain.Post;
import com.we.weblog.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


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
    @Select({"select count(*) from t_user where username=#{name} and password=#{ps}"})
    int selectByPassAndName(@Param("name") String username,@Param("ps") String password) throws RuntimeException;

    //TODO 修改密码
    @Insert({""})
    int saveByUser();

    @Update({" update t_user" +
            "set username = #{u.username}," +
            "password = #{u.password}"})
    int updateUser(@Param("u") User user);


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

}
