package com.taotao.controller;

import com.taotao.commom.EuTreeNode;
import com.taotao.commom.TaotaoResult;
import com.taotao.service.ContentCategoryList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by winsion on 2017/4/21.
 */
@Controller
public class ContentCategryController {

    @Autowired
    private ContentCategoryList categoryListService;


    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EuTreeNode> getContentCategory(@RequestParam(value = "id",defaultValue = "0") Long prantid){

        try {
            List<EuTreeNode> lists = categoryListService.getContentCategoryList(prantid);
            return lists;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult insterNewContentCategory(Long parentId,String name){

        try {
          TaotaoResult taotaoResult =  categoryListService.createNewNode(parentId,name);
          return taotaoResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/content/category/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(Long id,String name){

        try {
            TaotaoResult taotaoResult =  categoryListService.updateContentCategory(id,name);
            return taotaoResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/content/category/delete/{data}")
    @ResponseBody
    public TaotaoResult deleteContentCategory(@PathVariable String data){


        JSONObject jb=new JSONObject();
        JSONArray array=(JSONArray)jb.fromObject(data).get("allMenu");
        JSONObject o = (JSONObject) array.get(0);//获得第一个array结果
        String name=o.get("name").toString();//获得属性值

        try {
            TaotaoResult taotaoResult =  categoryListService.deleteContentCategory(data);
            return taotaoResult;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
