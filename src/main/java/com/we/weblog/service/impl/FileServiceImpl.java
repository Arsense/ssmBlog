package com.we.weblog.service.impl;

import com.we.weblog.domain.modal.Picture;
import com.we.weblog.service.FileService;
import com.we.weblog.util.FileUtil;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author tangwei9
 * @date 2019-03-28 13:50 2019-03-28 13:50
 */
@Service
public class FileServiceImpl implements FileService {

    /**
     * 上传图片
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public Picture loadPicture(HttpServletRequest request) throws Exception {
        String url;
        Picture picture = new Picture();
        try {
             url = FileUtil.picture(request);
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
