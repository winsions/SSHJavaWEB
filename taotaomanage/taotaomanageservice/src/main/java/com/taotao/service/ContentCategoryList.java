package com.taotao.service;

import com.taotao.commom.EuTreeNode;
import com.taotao.commom.TaotaoResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winsion on 2017/4/21.
 */
public interface ContentCategoryList {


//    获取分类列表

    List<EuTreeNode> getContentCategoryList(Long parentId) throws Exception;


    TaotaoResult createNewNode(Long parentId,String text) throws Exception;


    TaotaoResult updateContentCategory(Long id, String name);

    TaotaoResult deleteContentCategory(String data);
}
