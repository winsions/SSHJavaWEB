package com.taotao.controller;

import com.taotao.commom.EuDataJson;
import com.taotao.commom.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemSerivice;
import org.apache.zookeeper.data.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem tbItem,String item_desc ) throws Exception {

        TaotaoResult result = itemSerivice.creatItem(tbItem,item_desc);

        return result;
    }

    //通过商品的id取出商品的信息

    @RequestMapping("/rest/page/item-edit")
    @ResponseBody
    public TbItem getItembyId(Long id){

        TbItem item = itemSerivice.getItemById(id);
       return  item;


    }


    /**
     * 通过itemId获取商品描述
     * @param itemId
     * @return
     */
    @RequestMapping("/rest/item/query/item/desc/{itemId}")
    @ResponseBody
    public TaotaoResult getItemDescbyId(@PathVariable Long itemId){
        TaotaoResult taotaoResult =itemSerivice.getItemDescById(itemId);
        return taotaoResult;
    }



    /**
     * 通过itemId加载商品规格
     * @param itemId
     * @return
     */
    @RequestMapping("/rest/item/param/item/query/{itemId}")
    @ResponseBody
    public TaotaoResult getItemQuerybyId(@PathVariable Long itemId){
        TaotaoResult taotaoResult =itemSerivice.getItemQuerybyId(itemId);
        return taotaoResult;
    }

    /**
     * 通过itemId删除商品
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItemDescbyId(String ids){
        String[] idArray = ids.split(",");
        if (idArray.length ==0 ) return TaotaoResult.build(0,"未选择商品",null);
        //删除数据
        // TaotaoResult result = itemSerivice.deleteItemByIdA(idArray);


        //删除 status =3
        Byte status = (byte)3;
        TaotaoResult result = itemSerivice.instockItemByIdA(idArray,status);
        return result;
    }


    /**
     * 下架商品
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public TaotaoResult instockItemById(String ids){
        String[] idArray = ids.split(",");
        if (idArray.length ==0 ) return TaotaoResult.build(0,"未选择商品",null);
        //下架 status =2
        Byte status = (byte)2;
        TaotaoResult result = itemSerivice.instockItemByIdA(idArray,status);
        return result;

    }


    /**
     * 上架商品
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public TaotaoResult reshelfItemById(String ids){
        String[] idArray = ids.split(",");
        if (idArray.length ==0 ) return TaotaoResult.build(0,"未选择商品",null);
        //下架 status =2
        Byte status = (byte)1;
        TaotaoResult result = itemSerivice.instockItemByIdA(idArray,status);
        return result;


    }


}
