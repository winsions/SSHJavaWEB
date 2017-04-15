package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by winsion on 2017/4/15.
 */

@Controller

public class PageController {

    @RequestMapping("/")
    public String showIndex(){


        return "index";
    }

    @RequestMapping("/{page}")
    public String showOtherPage(@PathVariable String page){

        return page;

    }

}
