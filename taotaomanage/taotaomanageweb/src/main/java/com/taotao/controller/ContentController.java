package com.taotao.controller;

import com.taotao.commom.EuDataJson;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by winsion on 2017/4/24.
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EuDataJson getConteList(Long categoryId,Integer page,Integer rows){

        EuDataJson euDataJson = null;
        try {
            euDataJson = contentService.getcontentList(categoryId,page,rows);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return euDataJson;
    }

}
