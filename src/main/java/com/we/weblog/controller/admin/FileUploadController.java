package com.we.weblog.controller.admin;


import com.we.weblog.service.Impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class FileUploadController {


    @Autowired
    private FileService fileService;

    @PostMapping("/admin/upload")
    public  void uploadPicture(HttpServletRequest request){

        fileService.loadPicture(request);

    }
}
