package com.taotao.service.ItemServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.EuDataJson;
import com.taotao.commom.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by winsion on 2017/4/19.
 */
@Service
public class ItemparamSerivceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    public EuDataJson getitemParamService(Integer page, Integer rows) {
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        PageHelper.startPage(page,rows);

        List<TbItemParam> tbItemParams = tbItemParamMapper.selectItemParamAndItemCat(tbItemParamExample);
//        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

        EuDataJson euDataJson = new EuDataJson();
        euDataJson.setRows(tbItemParams);
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(tbItemParams);
        euDataJson.setTotal(pageInfo.getTotal());

        return euDataJson;
    }

    public TaotaoResult selectitemParamById(Long id) {

        TbItemParam tbItemParam = tbItemParamMapper.selectByPrimaryKey(id);

        if (tbItemParam== null) return TaotaoResult.ok();


        return TaotaoResult.build(404,"fault");
    }

    public TaotaoResult addItemParamById(TbItemParam tbItemParam) {

        try {
            Date date = new Date();
            tbItemParam.setCreated(date);
            tbItemParam.setUpdated(date);
            tbItemParamMapper.insert(tbItemParam);

        }catch (Exception e){
            throw new RuntimeException(e);
        }


        return TaotaoResult.ok();
    }

    public TaotaoResult deleteItemParamByIdA(String[] idItem) {


        try {
            tbItemParamMapper.deleteByIds(idItem);
        }catch (Exception e){

            throw new RuntimeException(e);
        }

        return TaotaoResult.ok();
    }
}
