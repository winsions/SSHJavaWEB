package com.taotao.service;

import com.taotao.commom.EuDataJson;
import com.taotao.pojo.TbItem;

/**
 * Created by winsion on 2017/4/14.
 */
public interface ItemSerivice {

    TbItem getItemById(long id);

    EuDataJson getItemList(Integer page, Integer rows);
}
