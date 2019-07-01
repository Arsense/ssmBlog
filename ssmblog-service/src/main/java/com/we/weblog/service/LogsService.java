package com.we.weblog.service;

import com.we.weblog.domain.Log;

import java.util.List;

/**
 * @author tangwei
 * @date 2018/10/23 19:34
 */
public interface LogsService {

    /**
     * 保存日志
     *
     * @return Log
     */
    int saveByLogs(Log log);

    /**
     * 根据id移除
     *
     * @param logId logId
     */
    void removeByLogsId(Integer logId);

    /**
     * 移除所有日志
     *
     */
    int removeAllLogs();

    /**
     * 查询最新的10条日志
     *
     * @return List
     */
    List<Log> findLastestTenLogs(int limit);



}
