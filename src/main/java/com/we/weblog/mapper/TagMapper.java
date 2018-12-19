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
    @Select({"select distinct tag_name from hexo_tag "})
    @ResultType(String.class)
    List<String> findAll();

    @Insert({
            "insert ignore into hexo_tag (tag_name,uid) values (#{tag},#{id})"})
    int insertBlogTag(@Param("tag") String tag,@Param("id") int id);


    @Delete({"delete from hexo_tag where uid = #{id}"})
    void  deleteTagById(@Param("id") int id);

    @Delete({"update hexo_post set tags = null where tags = #{tag}"})
    int deleleTagFromContext(@Param("tag") String tagName);


    @Delete({"delete from hexo_tag  where tag_name = #{tag}"})
    int deleteTagByName(@Param("tag") String tagName);

    @Insert({"insert into hexo_metas (name,type) values (#{m.name},#{m.type})"})
    int save(@Param("m")Metas name);

    @Select({"select distinct name from hexo_metas where type = 'category'"})
    List<Metas> selectCategories();


    @Delete({"delete from hexo_metas where name= #{name}"})
    int deleteCategoryByName(@Param("name")String name);


    @Select({"select name from hexo_metas  where type ='category'"})
    List<String> getAllCategories();
}
