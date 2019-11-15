package com.github.wb322.tools;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.RuntimeUtil;
import com.github.wb322.entity.Strategy;
import com.github.wb322.entity.Template;
import org.junit.Test;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wubo
 * @date 2019年11月11日 17:52
 */
public class TemplateUtil {
    /**
     * 模板文件id
     */
    private static Integer id = 0;
    /**
     * 模板文件集合,ztree格式
     */
    private static List<Template> list = new ArrayList<> ();
    /**
     * 获取模板列表
     * @return
     */
    public static List<Template> all() {
        id = 0;
        list.clear ();
        File file = new File (Strategy.templatesPath);
        //模板主目录
        if (file.isDirectory ()) {
            for (File f : file.listFiles ()) {
                boolean b;
                if (f.isDirectory ()){
                    b = true;
                }else{
                    b = false;
                }
                Template template = new Template (id++, f.getName (), null,b);
                list.add (template);
            }
        }
        return list;
    }
    /**
     * 获取模板详细信息
     * @return
     */
    public static List<Template> detail(String name) {
        id = 0;
        list.clear ();
        File file = new File (Strategy.templatesPath + name);
        if (file.isDirectory ()){
            list.add (new Template (id, file.getName (), null,true));
            getChildren(file);
        }else{
            list.add (new Template (id, file.getName (), null,false));
        }
        return list;
    }

    /**
     * 获取文件夹内的所有子文件夹和文件
     * @param file
     */
    public static void getChildren(File file) {
        Integer pId = id;
        for (File listFile : file.listFiles ()) {
            if (listFile.isDirectory ()){
                list.add (new Template (++id, listFile.getName (),pId,true));
                getChildren(listFile);
            }else{
                list.add (new Template (++id, listFile.getName (),pId,false));
            }
        }
    }

    /**
     * 用系统默认程序方式打开文件
     * @param url
     */
    public static void content(String url) {
        try {
            String type = "";
            url = Strategy.templatesPath + url;
            int i = url.lastIndexOf (".");
            if (i != -1){
                type = url.substring (i);
            }
            if (".sh".equals (type) || ".bat".equals (type)){
                RuntimeUtil.exec ("cmd /c start notepad " +  url);
            }else if(".exe".equals (type)){
            }else{
                RuntimeUtil.exec ("cmd /c start \"\" \""+url+"\"");
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }


    /**
     * 重命名文件
     * @param url
     * @param newName
     */
    public static boolean rename(String url, String newName) {
        boolean b =false;
        try {
            FileUtil.rename (new File (Strategy.templatesPath + url), newName, false, false);
            b = true;
        }catch (Exception e) {
           e.printStackTrace ();
        }
        return b;
    }

    /**
     * 删除文件或文件夹
     * @param url
     * @return
     */
    public static boolean delete(String url) {
        boolean b =false;
        try {
            FileUtil.del (Strategy.templatesPath + url);
            b = true;
        }catch (Exception e) {
            e.printStackTrace ();
        }
        return b;
    }

    /**
     * 添加文件或文件夹
     * @param type 0:文件夹,1:文件
     * @param url
     * @return
     */
    public static boolean add(Integer type, String url) {
        boolean b = false;
        try {
            if (type == 0){
                File file = new File (Strategy.templatesPath + url);
                file.mkdirs ();
                b = true;
            }else{
                FileUtil.touch (Strategy.templatesPath + url);
                b = true;
            }
        } catch (IORuntimeException e) {
            e.printStackTrace ();
        }
        return b;
    }

    @Test
    public void aaa(){
        File file = FileUtil.touch ("C:\\Users\\20688\\Desktop\\aaa\\bbb");
    }
}
