package com.we.weblog.service;

import com.we.weblog.domain.UploadPicture;
import com.we.weblog.tool.FileTools;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;


@Service
public class FileService {

    public UploadPicture loadPicture(HttpServletRequest request) throws Exception {

        String url = "";
        UploadPicture picture = new UploadPicture();
        try {
             url = FileTools.uploadPicture(request);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("update error");
        }

        if ("".equals(url)) {
            picture.setSuccess(0);
            picture.setUrl("");
            picture.setMessage("Upload fail");
            return picture;
        }
        picture.setSuccess(1);
        picture.setUrl(url);
        picture.setMessage("Upload success");

        return picture;

    }


}
