package com.taotao.controller;

import com.taotao.commom.EuDataJson;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by winsion on 2017/4/14.
 */
@Controller

public class ItemController {

    private final ItemSerivice itemSerivice;

    @Autowired
    public ItemController(ItemSerivice itemSerivice) {
        this.itemSerivice = itemSerivice;
    }

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId){


        TbItem item = itemSerivice.getItemById(itemId);
        System.out.println(item);

        return item;

    }

    @RequestMapping("/item/list")
    @ResponseBody
    public  EuDataJson getItemList(Integer page, Integer rows){
        EuDataJson euDataJson =  itemSerivice.getItemList(page,rows);

        return euDataJson;
    }

}
