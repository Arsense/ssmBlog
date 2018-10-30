package com.we.weblog.service.impl;

import com.we.weblog.domain.Log;
import com.we.weblog.mapper.LogMapper;
import com.we.weblog.service.LogsService;
import com.we.weblog.util.TimeUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class LogsServicesImpl implements LogsService {


    @Resource
    private LogMapper logMapper;

    /**
     * 保存日志
     *
     * @return Log
     */
    @Override
    public int saveByLogs(Log log) {
        return logMapper.addLog(log);
    }

    /**
     * 查询最新的五条日志
     *
     * @return List
     */
    @Override
    public List<Log> findLastestTenLogs(int limit) {
        if (limit < 0 || limit > 20) {
            limit = 10;
        }
        List<Log> logs= logMapper.getLogs(limit);
        for (Log log:logs) {
            log.setDateFormat(TimeUtil.getFormatClearToSecond(log.getCreated()));
        }
        return logs;
    }



    /**
     * 根据id移除
     *
     * @param logId logId
     */
    @Override
    public void removeByLogsId(Integer logId) {

    }
    /**
     * 移除所有日志
     *
     */
    @Override
    public void removeAllLogs() {

    }

}
