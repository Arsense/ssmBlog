package com.we.weblog.mapper;

import com.we.weblog.domain.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *   日志管理的Mapper
 */
@Repository
@Mapper
public interface LogMapper  {


        @Insert({"insert into t_logs (action,data,author_id,ip,created) " +
                "values (#{l.action},#{l.data},#{l.author_id},#{l.ip},#{l.created})"})
        int addLog(@Param("l") Log log);


        @Select({"select id,action,ip,created from t_logs order by id desc limit #{l}"})
        List<Log> getLogs(@Param("l") int limit);
}
