package com.taotao.service.ItemServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.EuDataJson;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by winsion on 2017/4/14.
 */
@Service
public class ItemSeriviceImpl implements ItemSerivice {

    @Autowired

    private TbItemMapper tbItemMapper;

    public TbItem getItemById(long id) {


        TbItem item = tbItemMapper.selectByPrimaryKey(id);


        //根据查询条件查询
        TbItemExample tbItemExample = new TbItemExample();


        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
        item = tbItems.get(0);

        System.out.println(tbItems.toArray().toString());

        return item;
    }

    public EuDataJson getItemList(Integer page, Integer rows) {
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page, rows);
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        EuDataJson euDataJson = new EuDataJson();
        euDataJson.setRows(tbItems);
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(tbItems);
        euDataJson.setTotal(pageInfo.getTotal());

        System.out.println(euDataJson);
        return euDataJson;
    }
}
