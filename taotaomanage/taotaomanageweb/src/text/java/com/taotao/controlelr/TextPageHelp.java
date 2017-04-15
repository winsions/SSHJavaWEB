package com.taotao.controlelr;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by winsion on 2017/4/15.
 */
public class TextPageHelp {

    @Test
    public void testPageHelperhfjjsaf(){
        //创建一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:Spring/applicationContext-*.xml");
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);

        PageHelper.startPage(1,10);

        TbItemExample example = new TbItemExample();
        List<TbItem> items = mapper.selectByExample(example);

        for (TbItem item :items) {
            System.out.println(item.getTitle());
        }

        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(items);
        long tatal = pageInfo.getTotal();
        System.out.println(tatal);

        System.out.println("hjdfksafjk");

    }


}
