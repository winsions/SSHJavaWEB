package com.taotao.controller;

import com.taotao.commom.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by winsion on 2017/4/18.
 */
@Controller
@RequestMapping("/pic")
public class PictureController {


    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public PictureResult pictureUpload(MultipartFile uploadFile){

      String name =  uploadFile.getOriginalFilename();
        System.out.println(name);
        PictureResult  pictureResult = pictureService.upLoadFile(uploadFile);
        return pictureResult;
    }
}
