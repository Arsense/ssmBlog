package com.we.weblog.mapper;


import com.we.weblog.domain.Metas;
import com.we.weblog.mapper.builder.TagSqlBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TagMapper {

    /**
     * 查询所有的标签
     *
     */
    @SelectProvider(type = TagSqlBuilder.class , method = "buildGetTagQuery")
    List<String> queryTags();


    @SelectProvider(type = TagSqlBuilder.class , method = "buildGetCategorysQuery")
    List<String> queryCategorys();



    @Insert({"insert ignore into hexo_tag (tag_name,uid) values (#{tag},#{id})"})
    int insertBlogTag(@Param("tag") String tag, @Param("id") int id);


    @Delete({"delete from hexo_tag where uid = #{id}"})
    void  deleteTagById(@Param("id") int id);

    @Delete({"update hexo_post set tags = null where tags = #{tag}"})
    int deleleTagFromContext(@Param("tag") String tagName);


    @Delete({"delete from hexo_tag  where tag_name = #{tag}"})
    int deleteTagByName(@Param("tag") String tagName);

    @Insert({"insert into hexo_metas (name,type) values (#{m.name},#{m.type})"})
    int save(@Param("m") Metas name);



    @Delete({"delete from hexo_metas where name= #{name}"})
    int deleteCategoryByName(@Param("name") String name);


}
