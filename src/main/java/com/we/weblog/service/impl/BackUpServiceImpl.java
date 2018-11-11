package com.we.weblog.service.impl;

import com.we.weblog.domain.BackFile;
import com.we.weblog.service.BackupService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author tangwei
 * @date 2018/11/11 20:49
 */
@Service
public class BackUpServiceImpl implements BackupService {


    @Override
    public List<BackFile> getBackUps(String fileType) {

        List<BackFile> backFiles = new ArrayList<>();
        BackFile backFile = null;

        //构造存储路径
        String sourcePath = System.getProperties().getProperty("user.home") + "/halo/backup/" + fileType;
        //创建文件对象
        File srcPath = new File(sourcePath);
        //获取文件下其他文件
        File[] files = srcPath.listFiles();

        //遍历文件
        if (null == files) {
            return backFiles;
        }

        for (File file : files) {
            if (file.isFile()) {
                backFile = new BackFile();
                backFile.setFileName(file.getName());
                //获取文件创建时间
                backFile.setCreateTime(getCreateTime(file.getAbsolutePath()));
                //获取文件类型
                backFile.setFileType("fileType");
                //获取文件大小
                backFile.setFileSize("100");
                backFile.setBackupType(fileType);
                backFiles.add(backFile);
            }
        }
        return backFiles;
    }

    public static Date getCreateTime(String filePath) {
        Path path = Paths.get(filePath);
        //获取文件属性
        BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        BasicFileAttributes attr;
        try {
            attr = basicview.readAttributes();
            return new Date(attr.creationTime().toMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 转换文件大小
     *
     * @param size size
     * @return String
     */
    public  String parseSize(long size) {
        //处理成B
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }

        //处理成KB
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        return null;
        //处理的有点奇怪
//        if (size < CommonParamsEnum.NOT_FOUND.getValue()) {
//            return String.valueOf(size) + "B";
//        } else {
//            size = size / 1024;
//        }
//        if (size < CommonParamsEnum.NOT_FOUND.getValue()) {
//            return String.valueOf(size) + "KB";
//        } else {
//            size = size / 1024;
//        }
//        if (size < CommonParamsEnum.NOT_FOUND.getValue()) {
//            size = size * 100;
//            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
//        } else {
//            size = size * 100 / 1024;
//            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
//        }


    }
}
