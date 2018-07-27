package com.we.weblog.tool;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class FileTools {


     private static Path getJavaResources() {

         String userDir = System.getProperty("user.dir") ;
         Path path = Paths.get(userDir  , "src" , "main" , "resources","static","img") ;
         return  path ;
     }
    /**
     *  获得文件前缀
     * @param fileName
     */
    private static String getSuffix(String fileName){
        String[] name = fileName.split("\\.");
        int length = name.length;

        if (length >0 ) {
            return name[length-1];
        } else {
            return "";
        }
    }

    public  static String uploadPicture(HttpServletRequest request) throws IOException {
        MultipartFile   files = getMultipartFile(request);
        //设置图片名称为currentTimeMillis+文件后缀
        String  fileName = String.valueOf(System.currentTimeMillis()+"."+FileTools.getSuffix(files.getOriginalFilename()));
        //然后设置路径
        String  date = TimeTool.getCurrentTime();


        String  path = getJavaResources().toString();
        //图片存储路径为根路径/年月。比如staic/picture/201608
        File filePath = new File(path);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        File targetFile = new File(filePath+"/"+fileName);

        //保存图片
        String requestUrl = getServerRoot(request);
        String tempPath = requestUrl+"/img"+"/"+fileName;
        //保存图片
        files.transferTo(targetFile);
        return tempPath;
    }


    /**
     *
     * 从HttpServletRequest中获取MultipartFile
     *
     * @param request
     * @return
     */
    private  static MultipartFile getMultipartFile(HttpServletRequest request){

        MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multipartRequest.getFileNames();
        return multipartRequest.getFile(fileNames.next());

    }

    /**
     * 获取web服务器访问url根路径
     */
    private static String getServerRoot(HttpServletRequest request) throws MalformedURLException {
        String serverRoot;
        try {
            serverRoot = new URL(request.getScheme(),request.getServerName(),request.getServerPort(),request.getContextPath()).toString();
        } catch(MalformedURLException e) {
            throw  new MalformedURLException("get ServerRoot error");
        }
        return serverRoot;
    }


}
