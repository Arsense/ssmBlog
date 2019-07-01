package com.we.weblog.mapper.builder;

/**
 * @author Clay
 * @date 2019/7/1 23:57
 */
public class AttachmentSqlBuilder {


    public String buildAttachmentQuery() {
        String sql = "select * from hexo_attach limit #{page},#{size}";
        return sql;
    }


}
