package com.taotao.controller;

import com.taotao.commom.EuDataJson;
import com.taotao.commom.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by winsion on 2017/4/19.
 */
@Controller
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;


    @RequestMapping("/item/param/list")

    @ResponseBody
    public EuDataJson getItemParamList(Integer page, Integer rows) {

        EuDataJson euDataJson = itemParamService.getitemParamService(page, rows);

        return euDataJson;

    }

    /**
     * 判断条目是否添加
     *
     * @param itemcatid
     * @return
     */
    @RequestMapping("/item/param/query/itemcatid/{itemcatid}")
    @ResponseBody
    public TaotaoResult selectItemParamById(@PathVariable Long itemcatid) {
        TaotaoResult taotaoResult = itemParamService.selectitemParamById(itemcatid);
        return taotaoResult;
    }

    /**
     * 添加条目
     *
     * @param cid
     * @return
     */
    @RequestMapping("/item/param/save/{cid}")
    @ResponseBody
    public TaotaoResult addItemParamById(@PathVariable Long cid, String paramData) {

        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        return itemParamService.addItemParamById(tbItemParam);
    }

    @RequestMapping("/item/param/delete")
    @ResponseBody
    public TaotaoResult deleteItemparaByIds(String ids){
        String[] idArray = ids.split(",");
       TaotaoResult taotaoResult = itemParamService.deleteItemParamByIdA(idArray);
       return taotaoResult;

    }
}
