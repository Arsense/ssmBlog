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
    List<Log> queryLogs(@Param("l") int limit);

    @InsertProvider(type = LogSqlBuilder.class, method = "buildDelete")
    int saveLog(@Param("l") Log log);


    @DeleteProvider(type = LogSqlBuilder.class, method = "buildDelete")
    int clearLog();
}
