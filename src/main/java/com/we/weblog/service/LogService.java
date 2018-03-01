package com.we.weblog.service;


import com.we.weblog.domain.Log;
import com.we.weblog.mapping.LogMapper;
import com.we.weblog.tool.TimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;


    LogService(LogMapper logMapper){
        this.logMapper = logMapper;
    }


    /**
     * 添加日志
     * @param log
     * @return
     */
    public int addLog(Log log){

        return logMapper.addLog(log);

    }

    /**
     *  得到最近的操作日志
     * @param limit
     * @return
     */
    public List<Log> getLogPages(int limit){
        if(limit < 0||limit >20) {
         limit = 10;
        }
        List<Log> logs= logMapper.getLogs(limit);
        for(Log log:logs){
            log.setDateFormat(TimeTool.getFormatClearToSecond(log.getCreated()));
        }

        return logs;
    }


}
