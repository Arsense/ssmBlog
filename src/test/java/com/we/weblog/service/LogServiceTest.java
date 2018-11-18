package com.we.weblog.service;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author tangwei
 * @date 2018/11/18 23:23
 */

public class LogServiceTest extends BaseTest {


    @Resource
    private LogsService logsService;

    @Test
    public void testClear(){
        int a = logsService.removeAllLogs();

        if(a > 0){
            System.out.println("删除日志成功");
        } else {
            System.out.println("删除失败");
        }
    }
}
