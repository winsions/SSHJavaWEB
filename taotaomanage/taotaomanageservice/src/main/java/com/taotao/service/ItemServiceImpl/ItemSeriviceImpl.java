package com.taotao.service.ItemServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.EuDataJson;
import com.taotao.commom.IDUtils;
import com.taotao.commom.TaotaoResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by winsion on 2017/4/14.
 */
@Service
public class ItemSeriviceImpl implements ItemSerivice {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    public TbItem getItemById(long id) {


        TbItem item = tbItemMapper.selectByPrimaryKey(id);


        //根据查询条件查询
        TbItemExample tbItemExample = new TbItemExample();


        TbItemExample.Criteria criteria = tbItemExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
        item = tbItems.get(0);

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

        return euDataJson;
    }

    public TaotaoResult creatItem(TbItem tbItem, String item_desc) throws Exception {

        Long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        Date date = new Date();

        TaotaoResult result = insertItemDesc(itemId, date, item_desc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }

        // '商品状态，1-正常，2-下架，3-删除',
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        tbItemMapper.insert(tbItem);


        return TaotaoResult.ok();
    }

    public TaotaoResult getItemDescById(Long id) {

        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);

        String msg;
        Object date;
        Integer status;
        if (tbItemDesc == null){
            status = 404;
            msg = "fause";
            date = null;
        }else {
            status = 200;
            msg = "success";
            date = tbItemDesc.getItemDesc();
        }
        TaotaoResult taotaoResult = TaotaoResult.build(status,msg,date);

        return taotaoResult;
    }

    /**
     * 删除商品,修改状态为3
     * @param idsArray
     * @return
     */
    public TaotaoResult deleteItemByIdA(String[] idsArray) {

        for (String id : idsArray) {
            int hah = tbItemMapper.deleteByPrimaryKey(Long.parseLong(id));
            System.out.println(hah);
        }
        return TaotaoResult.ok();
    }


    public TaotaoResult instockItemByIdA(String[] idArray, Byte status) {

        try {
            for (String id : idArray) {

                Date date = new Date();
                TbItemExample tbItemExample = new TbItemExample();
                TbItemExample.Criteria criteria = tbItemExample.createCriteria();
                criteria.andIdEqualTo(Long.parseLong(id));
                TbItem tbItem = new TbItem();
                tbItem.setStatus(status);
//               int haha = tbItemMapper.updateByExample();

                int haha = tbItemMapper.updateByExampleSelective(tbItem, tbItemExample);
                System.out.println(haha);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return TaotaoResult.ok();

    }

    public TaotaoResult getItemQuerybyId(Long itemId) {

        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(itemId);

       List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

       TbItemParam tbItemParam = tbItemParams.get(0);
       if (tbItemParam == null){
           return TaotaoResult.build(404,"false");
       }else {
           return TaotaoResult.ok(tbItemParam.getParamData());
       }

    }


    /**
     * 添加商品描述
     *
     * @param itemId
     * @param date
     * @param item_desc
     * @return
     */
    public TaotaoResult insertItemDesc(Long itemId, Date date, String item_desc) {
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(item_desc);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setCreated(date);

        tbItemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();

    }


}
