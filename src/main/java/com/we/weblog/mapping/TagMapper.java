package com.we.weblog.mapping;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

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
    List<String> showAllTags();


    @Insert({
            "insert ignore into t_tag (tag_name,uid) values (#{tag},#{id})"})
    int insertBlogTag(@Param("tag") String tag,@Param("id") int id);


    @Delete({"delete t_tag where id = #{id}"})
    void  deleteTagsById(@Param("id") int id);



    //distinct 不重复的
    @Select({"select distinct tag_name from t_tag "})
    @ResultType(String.class)
    List<String> selectTagkinds();
}
