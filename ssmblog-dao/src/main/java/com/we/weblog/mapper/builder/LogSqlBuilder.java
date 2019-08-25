package com.we.weblog.mapper.builder;

/**
 * @author Clay
 * @date 2019/7/1 23:57
 */
public class LogSqlBuilder {

    public String buildGetLogsQuery(){ return "select id,action,ip,created from hexo_logs order by id desc limit #{l}"; }


    public String buildDelete(){ return "delete from hexo_logs"; }


    public String buildInsert(){return "insert into hexo_logs (action,data,author_id,ip,created) " +
            "values (#{l.action},#{l.data},#{l.author_id},#{l.ip},#{l.created})"; }
}
