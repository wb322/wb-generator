package com.github.wb322.controller;

import com.github.wb322.entity.Template;
import com.github.wb322.tools.Result;
import com.github.wb322.tools.TemplateUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wubo
 * @date 2019年11月11日 17:49
 */
@RestController
@RequestMapping("/templates")
public class TemplateController {

    @GetMapping
    public Result getTemplates(){
        List<Template> all = TemplateUtil.all ();
        return new Result (0,"",all);
    }
    @GetMapping("/detail")
    public Result getDetail(String name){
        List<Template> detail = TemplateUtil.detail (name);
        return new Result (0,"",detail);
    }
    @GetMapping("/content")
    public Result getContent(String url){
        TemplateUtil.content (url);
        return new Result (0,"",null);
    }
    @PostMapping
    public Result add(Integer type,String url){
        boolean b = TemplateUtil.add(type,url);
        if (b){
            return new Result (0,"",null);
        }else{
            return new Result (1,"添加出错",null);
        }
    }
    @PutMapping
    public Result rename(String url,String newName){
        boolean b = TemplateUtil.rename (url, newName);
        if (b){
            return new Result (0,"",null);
        }else{
            return new Result (1,"修改名称出错",null);
        }
    }
    @DeleteMapping
    public Result delete(String url){
        boolean b = TemplateUtil.delete (url);
        if (b){
            return new Result (0,"",null);
        }else{
            return new Result (1,"删除文件出错",null);
        }
    }
}
