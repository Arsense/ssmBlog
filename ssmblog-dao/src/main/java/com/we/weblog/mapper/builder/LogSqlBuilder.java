package com.we.weblog.mapper.builder;

/**
 * @author Clay
 * @date 2019/7/1 23:57
 */
public class LogSqlBuilder {

    public String buildGetLogsQuery(){
        return "select id,action,ip,created from hexo_logs order by id desc limit #{l}";
    }
}
