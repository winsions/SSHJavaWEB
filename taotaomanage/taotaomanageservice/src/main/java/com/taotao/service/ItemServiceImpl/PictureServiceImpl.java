package com.taotao.service.ItemServiceImpl;

import com.taotao.commom.FtpClint.FtpUnit;
import com.taotao.commom.FtpClint.imageNameCreate;
import com.taotao.commom.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by winsion on 2017/4/18.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
//    @Value("${FILI_UPLOAD_PATH}")
//    private String FILI_UPLOAD_PATH;
    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;




    public PictureResult upLoadFile( MultipartFile uploadFile) {


        //上传文件功能实现
        String path = savePicture(uploadFile);
        //回显

        Integer error = 0;
        if(path== null) error = 1;
        PictureResult result = new PictureResult();
        result.setError(error);
        result.setUrl(path);
        return result;
    }



    private String savePicture(MultipartFile uploadFile) {
        //上传文件功能

//        //判断文件是否为空
//        if (uploadFile.isEmpty()){
//
//        }

        // 上传文件以日期为单位分开存放，可以提高图片的查询速度

        String filePath = "/" + new SimpleDateFormat("yyyy").format(new Date()) + "/"
                + new SimpleDateFormat("MM").format(new Date()) + "/"
                + new SimpleDateFormat("dd").format(new Date());

        // 取原始文件名
        String originalFilename = uploadFile.getOriginalFilename();

        //新文件名
        String newFileName = imageNameCreate.getImageName()+".png";
        //上传FTP
        boolean result = false;
        try {
            result = FtpUnit.upLoadFile(FTP_HOST,FTP_PORT,FTP_USERNAME,FTP_PASSWORD,
                    uploadFile.getInputStream(),FTP_BASE_PATH,filePath,newFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (!result) return null;
        return filePath+"/"+newFileName;

    }
}
