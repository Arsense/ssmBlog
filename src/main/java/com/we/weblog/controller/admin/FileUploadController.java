package com.we.weblog.controller.admin;


import com.we.weblog.domain.UploadPicture;
import com.we.weblog.service.Impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class FileUploadController {


    @Autowired
    private FileService fileService;

    @PostMapping("/admin/upload")
    @ResponseBody
    public  UploadPicture uploadPicture(HttpServletRequest request) throws Exception {
        //未test

        UploadPicture picture = fileService.loadPicture(request);

        return picture;
    }
}
