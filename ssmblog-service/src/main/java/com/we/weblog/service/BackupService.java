package com.we.weblog.service;

import com.we.weblog.domain.BackFile;

import java.util.List;

/**
 * @author Clay
 * @date 2018/11/11 20:49
 */
public interface BackupService {

    /**
     * 获取备份文件信息
     * @param filePath
     * @return
     */
    List<BackFile> getBackUps(String filePath);

    /**
     * 备份资源文件
     *
     */
    void backupResources();

    /**
     * 备份数据库
     *
     */
    void backupDatabase();

    /**
     *  备份文章，导出markdown文件
     *
     */
    void backupPosts();




}
