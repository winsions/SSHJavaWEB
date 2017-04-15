package com.taotao.service.ItemServiceImpl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by winsion on 2017/4/14.
 */
@Service
public class ItemSeriviceImpl implements ItemSerivice {

    @Autowired

    private TbItemMapper tbItemMapper;

    public TbItem getItemById(long id) {


        TbItem item = tbItemMapper.selectByPrimaryKey(id);

        return item;
    }
}
