package com.we.weblog.mapper.builder;

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


}
