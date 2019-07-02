package com.we.weblog.mapper.builder;

import com.we.weblog.domain.Tags;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author Clay
 * @date 2019/7/1 23:58
 */
public class TagSqlBuilder {

    public String buildGetTagQuery () {
        return "select distinct tag_name from hexo_tag ";
    }


    public String buildGetCategorysQuery () {
        return "select name from hexo_metas  where type ='category'";
    }


    public String buildDelete(Tags tag) {
        return new SQL(){{
            DELETE_FROM("hexo_tag");
            if (tag.getTagId() > 0) {
                WHERE("uid = #{id}");
            }
            if (tag.getTagName() != null) {
                WHERE("tag_name = #{tag}");
            }
        }}.toString();

    }

    public String buildInsert() {
        return "insert ignore into hexo_tag (tag_name,uid) values (#{tag},#{id})";
    }


    public String buildDeletePostTag() {
       return  "update hexo_post set tags = null where tags = #{tag}";
    }

    public String buildMetaInsertQuery() {
        return "insert into hexo_metas (name,type) values (#{m.name},#{m.type})";
    }

    public String buildMetaDelete() {
        return "delete from hexo_metas where name= #{name}";
    }

}
