package com.taotao.service;

import com.taotao.commom.EuDataJson;
import com.taotao.commom.TaotaoResult;
import com.taotao.pojo.TbItemParam;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by winsion on 2017/4/19.
 */
public interface ItemParamService {

    EuDataJson getitemParamService(Integer page,Integer rows);

    TaotaoResult selectitemParamById(Long id);


    TaotaoResult addItemParamById(TbItemParam tbItemParam);


    TaotaoResult deleteItemParamByIdA(String[] idItem);
}
