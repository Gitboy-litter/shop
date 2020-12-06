package com.chen.xbshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ProjectName: shop
 * @Package: com.chen.xbshop
 * @Author: ChenZengWen
 * @Description: 描述
 * @Date: 2020/12/6 11:17
 * @Version: 1.0
 */
@RestController
@RequestMapping("/main")
public class MainController {
    @RequestMapping("head")
    public ModelAndView head() {
        return new ModelAndView("/head.html");
    }

    @RequestMapping("hot")
    public ModelAndView hot() {
        return new ModelAndView("/hot.html");
    }
}
