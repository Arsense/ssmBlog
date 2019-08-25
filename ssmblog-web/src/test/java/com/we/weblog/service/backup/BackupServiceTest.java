package com.we.weblog.service.backup;

import com.we.weblog.BaseTest;
import com.we.weblog.domain.enums.FileTypeEnum;
import com.we.weblog.service.BackupService;
import org.junit.Test;

import javax.annotation.Resource;


/**
 *
 * @author Clay
 * @date 2019/3/28 14:09
 */
public class BackupServiceTest extends BaseTest {

    @Resource
    private BackupService backupService;

    @Test
    public void getBackUps() {
        backupService.getBackUps(FileTypeEnum.RESOURCE.getKey());
        backupService.getBackUps(FileTypeEnum.DATABASES.getKey());
        backupService.getBackUps(FileTypeEnum.RESOURCE.getKey());
    }

    @Test
    public void backupResources() {
        backupService.backupResources();
    }

    @Test
    public void backupDatabase() {
        backupService.backupDatabase();
    }

    @Test
    public void backupPosts() {
        backupService.backupPosts();
    }
}