package com.we.weblog.mapper;

import com.we.weblog.domain.Log;
import com.we.weblog.mapper.builder.LogSqlBuilder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *   日志管理的Mapper
 */
@Repository
@Mapper
public interface LogMapper  {


    @SelectProvider(type = LogSqlBuilder.class, method = "buildGetLogsQuery")
    List<Log> getLogs(@Param("l") int limit);

    @Insert({"insert into hexo_logs (action,data,author_id,ip,created) " +
            "values (#{l.action},#{l.data},#{l.author_id},#{l.ip},#{l.created})"})
    int addLog(@Param("l") Log log);





    @Delete({"delete from hexo_logs"})
    int removeAllLog();
}
