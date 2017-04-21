package com.taotao.service;

import com.taotao.commom.EuDataJson;
import com.taotao.commom.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

/**
 * Created by winsion on 2017/4/14.
 */
public interface ItemSerivice {

    TbItem getItemById(long id);

    EuDataJson getItemList(Integer page, Integer rows);

    TaotaoResult creatItem(TbItem tbItem,String item_desc) throws Exception;


    /*
    加载商品描述
     */
    TaotaoResult getItemDescById(Long id);

    /*
    加载商品规格
     */


    /*
    删除商品
     */

    TaotaoResult deleteItemByIdA(String[] idsArray);

    TaotaoResult instockItemByIdA(String[] idArray,Byte status);

    TaotaoResult getItemQuerybyId(Long itemId);
}
