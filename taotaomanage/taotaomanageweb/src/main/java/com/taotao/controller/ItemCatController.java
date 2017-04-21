package com.taotao.controller;

import com.taotao.commom.EuTreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by winsion on 2017/4/15.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EuTreeNode> getCatList(@RequestParam(value = "id",defaultValue = "0") Long parrentId ){

        List<EuTreeNode> list = itemCatService.getCastList(parrentId);
        return list;
    }

}
