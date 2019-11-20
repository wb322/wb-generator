package com.github.wb322.controller;

import com.github.wb322.service.TableService;
import com.github.wb322.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wubo
 * @date 2019年11月11日 9:32
 */
@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public Result getTables(Integer page,Integer size){
        return new Result (0,"查询成功",tableService.getTables (),10);
    }
    @PostMapping
    public Result generator(String template){
        return null;
    }
}
