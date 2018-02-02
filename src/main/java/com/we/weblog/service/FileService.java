package com.we.weblog.service;

import com.we.weblog.domain.UploadPicture;
import com.we.weblog.tool.FileTools;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;


@Service
public class FileService {

    public UploadPicture loadPicture(HttpServletRequest request) throws Exception {
        String url = "";
        UploadPicture pic = new UploadPicture();
        try {
             url = FileTools.uploadPicture(request);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("update error");
        }

        if("".equals(url)){
            pic.setSuccess(0);
            pic.setUrl("");
            pic.setMessage("Upload fail");
            return pic;
        }
        pic.setSuccess(1);
        pic.setUrl(url);
        pic.setMessage("Upload success");

        return pic;

    }


}
