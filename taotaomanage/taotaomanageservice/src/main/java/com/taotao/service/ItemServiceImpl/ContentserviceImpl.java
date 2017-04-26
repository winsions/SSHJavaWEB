package com.taotao.service.ItemServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.EuDataJson;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by winsion on 2017/4/24.
 */
@Service
public class ContentserviceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

    public EuDataJson getcontentList(Long categoryId, Integer page,
                                     Integer rows) throws Exception{

        TbContentExample tbContenExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContenExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        PageHelper.startPage(page,rows);
        List<TbContent> list = tbContentMapper.selectByExample(tbContenExample);
        EuDataJson euDataJson =new EuDataJson();
        PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
        euDataJson.setTotal(pageInfo.getTotal());
        euDataJson.setRows(list);
        return euDataJson;
    }
}
