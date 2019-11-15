package com.github.wb322.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wubo
 * @date 2019年11月11日 10:52
 */
@RequestMapping
public class IndexController {
    @GetMapping
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView ("index.html");
        return modelAndView;
    }
}
