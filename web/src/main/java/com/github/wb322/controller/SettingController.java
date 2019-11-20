package com.github.wb322.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.Setting;
import com.github.wb322.entity.Strategy;
import com.github.wb322.tools.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @author wubo
 * @date 2019年11月18日 9:43
 */
@RestController
@RequestMapping("/settings")
public class SettingController {

    @GetMapping
    public Result getSettings(){
        Setting setting = new Setting (FileUtil.touch (Strategy.settingsPath), CharsetUtil.CHARSET_UTF_8,false);
        Set<Map.Entry<String, String>> entries = setting.entrySet ();
        return new Result (0,"",entries);
    }
    @PostMapping
    public Result getSettings(String name,String value){


        Setting setting = new Setting (FileUtil.touch (Strategy.settingsPath), CharsetUtil.CHARSET_UTF_8,false);
        Set<Map.Entry<String, String>> entries = setting.entrySet ();
        return new Result (0,"",entries);
    }
}
