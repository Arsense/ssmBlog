package com.we.weblog.service.logs;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.Log;
import com.we.weblog.service.LogsService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author Clay
 * @date 2019/3/28 15:10
 */
public class LogsServiceTest extends BaseTest {

    @Resource
    private LogsService logsService;


    @Test
    public void saveByLogs() {
        logsService.saveByLogs(new Log());
    }

    @Test
    public void removeByLogsId() {
        logsService.removeByLogsId(1);

    }

    @Test
    public void removeAllLogs() {
        logsService.removeAllLogs();

    }

    @Test
    public void findLastestTenLogs() {
        logsService.findLastestTenLogs(1);

    }
}