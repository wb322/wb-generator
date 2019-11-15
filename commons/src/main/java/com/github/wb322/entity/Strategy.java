package com.github.wb322.entity;

import java.io.File;
import java.util.Map;

/**
 * @author wubo
 * @date 2019年11月11日 15:39
 */
public class Strategy {
    /**
     * 根据表生成实体类时前缀替换
     * 不填写使用驼峰格式   eg:sys_user ->SysUser
     * prefix = sys_    eg:sys_user ->User
     * @return
     */
    public static Map prefix;
    /**
     * 项目路径
     */
    public static final String projectPath = new File ("").getAbsolutePath ();
    /**
     * 模板路径
     */
    public static final String templatesPath = projectPath + File.separator + "templates" + File.separator;

}
