package com.we.weblog.service.impl;

import com.we.weblog.domain.UploadPicture;
import com.we.weblog.service.FileService;
import com.we.weblog.tool.FileTools;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;


@Service
public class FileServiceImpl implements FileService {

    /**
     * 上传图片
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public UploadPicture loadPicture(HttpServletRequest request) throws Exception {
        String url;
        UploadPicture picture = new UploadPicture();
        try {
             url = FileTools.uploadPicture(request);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception("loadPicture() error , load picture error");
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
