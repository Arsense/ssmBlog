package com.we.weblog.mapper;


import com.we.weblog.domain.Metas;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface TagMapper {

    /**
     *  显示所有的tags
     * @return
     */
    @Select({"select distinct tag_name from t_tag "})
    @ResultType(String.class)
    List<String> findAll();

    @Insert({
            "insert ignore into t_tag (tag_name,uid) values (#{tag},#{id})"})
    int insertBlogTag(@Param("tag") String tag,@Param("id") int id);


    @Delete({"delete from t_tag where uid = #{id}"})
    void  deleteTagById(@Param("id") int id);

    @Delete({"update t_context set tags = null where tags = #{tag}"})
    int deleleTagFromContext(@Param("tag") String tagName);


    @Delete({"delete from t_tag  where tag_name = #{tag}"})
    int deleteTagByName(@Param("tag") String tagName);

    @Insert({"insert into t_mates (name,type) values (#{m.name},#{m.type})"})
    int save(@Param("m")Metas name);

    @Select({"select distinct name from t_mates where type = 'category'"})
    List<Metas> selectCategories();


    @Delete({"delete from t_mates where name= #{name}"})
    int deleteCategoryByName(@Param("name")String name);


    @Select({"select name from t_mates  where type ='category'"})
    List<String> getAllCategories();
}
