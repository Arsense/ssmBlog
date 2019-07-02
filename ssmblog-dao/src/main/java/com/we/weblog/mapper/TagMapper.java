package com.we.weblog.mapper;


import com.we.weblog.domain.Metas;
import com.we.weblog.domain.Tags;
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


    @DeleteProvider(type = TagSqlBuilder.class , method = "buildDelete")
   int deleteTag(Tags tag);


    @InsertProvider(type = TagSqlBuilder.class , method = "buildInsert")
    int insertBlogTag(@Param("tag") String tag, @Param("id") int id);


    @DeleteProvider(type = TagSqlBuilder.class , method = "buildDeletePostTag")
    int delTagInPost(@Param("tag") String tagName);


    @InsertProvider(type = TagSqlBuilder.class , method = "buildMetaInsertQuery")
    int save(@Param("m") Metas name);


    @DeleteProvider(type = TagSqlBuilder.class , method = "buildMetaDelete")
    int deleteCategoryByName(@Param("name") String name);


}
