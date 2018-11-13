package com.we.weblog.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.we.weblog.domain.BackFile;
import com.we.weblog.domain.Post;
import com.we.weblog.service.BackupService;
import com.we.weblog.service.PostService;
import com.we.weblog.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
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

    private static final Logger LOG = LoggerFactory.getLogger(BackUpServiceImpl.class);

    @Resource
    private PostService postService;

    @Override
    public List<BackFile> getBackUps(String fileType) {

        List<BackFile> backFiles = new ArrayList<>();
        BackFile backFile = null;

        //构造存储路径
        String sourcePath = System.getProperties().getProperty("user.home") + "\\blog\\backup\\" + fileType;
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

    @Override
    public void backupResources() {
        try {
            if (getBackUps("resources").size() > 10 ) {
                FileUtil.del(System.getProperties().getProperty("user.home") + "/blog/backup/resources/");
            }
            //获取静态资源地址
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            //取出其绝对路径
            String srcPath = path.getAbsolutePath();
            //创建备份时间和时间戳 //执行打包
            String distName = "resources_backup_" + TimeUtil.getCurrentTime();
            ZipUtil.zip(srcPath, System.getProperties().getProperty("user.home") + "/blog/backup/resources/" + distName + ".zip");

            LOG.info("当前时间：{}，执行了资源文件备份。", DateUtil.now());

        } catch (Exception e) {
            LOG.error("备份文章失败：{}", e.getMessage());

        }
    }

    @Override
    public void backupDatabase() {
        try{
            //备份不超过10个
            if (this.getBackUps("databases").size() > 10) {
                FileUtil.del(System.getProperties().getProperty("user.home") + "/halo/backup/databases/");
            }
            String srcPath = System.getProperties().getProperty("user.home") + "/blog/";
            String distName = "databases_backup_" + TimeUtil.getCurrentTime();
            ZipUtil.zip(srcPath + "sql.mv.db", System.getProperties().getProperty("user.home") + "/blog/backup/databases/" + distName + ".zip");
            LOG.info("当前时间：{}，执行了数据库备份。", DateUtil.now());

        } catch (Exception e) {
            LOG.error("备份数据库失败：{}", e.getMessage());
        }
    }

    @Override
    public void backupPosts() {
        //再处理一下
        List<Post> posts = postService.findAllPosts(5);
        try {
            if (getBackUps("post").size() > 10) {
                FileUtil.del(System.getProperties().getProperty("user.home") + "/halo/backup/posts/");
            }
            //打包好的文件名
            String distName = "posts_backup_" + TimeUtil.getCurrentTime();
            String srcPath = System.getProperties().getProperty("user.home") + "/halo/backup/posts/" + distName;
//            for (Post post : posts) {
//                HaloUtils.postToFile(post.getPostContentMd(), srcPath, post.getPostTitle() + ".md");
//            }
            //打包导出好的文章
            ZipUtil.zip(srcPath, srcPath + ".zip");
            FileUtil.del(srcPath);
            LOG.info("当前时间：{}，执行了文章备份。", DateUtil.now());
        } catch (Exception e) {
            LOG.error("备份文章失败：{}", e.getMessage());

        }


    }


    /**
     * 得到文件创建时间
     *
     * @param filePath
     * @return
     */
    private static Date getCreateTime(String filePath) {
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
