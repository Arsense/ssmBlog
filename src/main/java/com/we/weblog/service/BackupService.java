package com.we.weblog.service;

import com.we.weblog.domain.BackFile;

import java.util.List;

/**
 * @author tangwei
 * @date 2018/11/11 20:49
 */
public interface BackupService {

    /**
     * 获取备份文件信息
     * @param filePath
     * @return
     */
    List<BackFile> getBackUps(String filePath);

}
