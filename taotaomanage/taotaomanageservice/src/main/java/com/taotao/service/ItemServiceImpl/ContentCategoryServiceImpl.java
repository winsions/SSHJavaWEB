package com.taotao.service.ItemServiceImpl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.taotao.commom.EuTreeNode;
import com.taotao.commom.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by winsion on 2017/4/21.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryList {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    public List<EuTreeNode> getContentCategoryList(Long parentId) throws Exception{

        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);

        List<EuTreeNode> resultList = new ArrayList<EuTreeNode>();
        for (TbContentCategory tbContentCategory :list) {
            EuTreeNode euTreeNode =new EuTreeNode();
            euTreeNode.setId(tbContentCategory.getId());
            euTreeNode.setText(tbContentCategory.getName());
            //判断是否是父节点
            if (tbContentCategory.getIsParent()){
                euTreeNode.setState("closed");
            }else {
                euTreeNode.setState("open");
            }

            resultList.add(euTreeNode);
        }

        return resultList;
    }

    public TaotaoResult createNewNode(Long parentId, String text) throws Exception{


        //更改父节点的状态
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andIdEqualTo(parentId);
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setIsParent(true);
        tbContentCategoryMapper.updateByExampleSelective(tbContentCategory,tbContentCategoryExample);


        //添加数据库
        //创建一个pojo
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(text);
        contentCategory.setIsParent(false);
        //'状态。可选值:1(正常),2(删除)',
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        tbContentCategoryMapper.insert(contentCategory);



        return TaotaoResult.ok(contentCategory);
    }

    public TaotaoResult updateContentCategory(Long id, String name) {

        //更改父节点的状态
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andIdEqualTo(id);
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setName(name);
        tbContentCategoryMapper.updateByExampleSelective(tbContentCategory,tbContentCategoryExample);


        return null;
    }

    public TaotaoResult deleteContentCategory(String data) {


        System.out.println(data.toString());


        return null;
    }
}
