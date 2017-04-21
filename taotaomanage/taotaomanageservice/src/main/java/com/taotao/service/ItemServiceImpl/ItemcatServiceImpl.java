package com.taotao.service.ItemServiceImpl;

import com.taotao.commom.EuTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winsion on 2017/4/15.
 */
@Service
public class ItemcatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    public List<EuTreeNode> getCastList(long parentId) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();

        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();

        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample);

        List<EuTreeNode> result =  new ArrayList<EuTreeNode>();


        for (TbItemCat tbItemCat :list) {
            EuTreeNode euTreeNode = new EuTreeNode();
            euTreeNode.setId(tbItemCat.getId());
            euTreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            euTreeNode.setText(tbItemCat.getName());
            result.add(euTreeNode);
        }


        return result;
    }
}
