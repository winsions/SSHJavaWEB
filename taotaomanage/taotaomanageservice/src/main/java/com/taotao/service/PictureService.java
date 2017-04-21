package com.taotao.service;

import com.taotao.commom.PictureResult;
import org.springframework.web.multipart.MultipartFile;



/**
 * Created by winsion on 2017/4/18.
 */
public interface PictureService {

    PictureResult  upLoadFile(MultipartFile uploadFile);

}
