package com.github.wb322.tools;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import com.github.wb322.entity.Strategy;
import com.github.wb322.entity.Table;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wubo
 * @date 2019年11月19日 14:16
 */
public class GeneratorUtil {

    @Test
    public void startGenerator()throws Exception{
        String name = Strategy.templatesPath + "SpringBoot单模块+layuiadmin";
        String path = "com.github.test";
        File templateDir = new File (name);
        File baseDir = FileUtil.mkdir ("C:\\Users\\20688\\Desktop\\test");

        List<Table> tables = new ArrayList<> ();
        tables.add (new Table ("test","Test"));
        tables.add (new Table ("test2","Test2"));
        copyFile(templateDir,baseDir,false,tables);
    }

    /**
     * 生成文件
     * @param src
     * @param dest
     * @param isOverride
     * @param tables
     * @throws Exception
     */
    private void copyFile(File src, File dest, boolean isOverride,List<Table> tables)throws Exception{
        boolean b = false;
        if (src.isDirectory ()){
            if (src.getName ().contains ("[Table2]")){
                b = true;
            }
            File[] files = src.listFiles ();
            if (ArrayUtil.isNotEmpty (files)){
                notEmptyDir(files,dest,b,isOverride,tables);

            }else{
                emptyDir(dest,b,tables);
            }
        }else{
            if (src.getName ().contains ("[Table2]")){
                b = true;
            }
            writeContent(src,dest,isOverride,b,tables);
        }
    }

    /**
     * 递归生成非空文件夹,文件夹中有文件生成文件
     * @param files
     * @param dest
     * @param b
     * @param isOverride
     * @param tables
     * @throws Exception
     */
    private void notEmptyDir(File[] files, File dest, boolean b, boolean isOverride, List<Table> tables)throws Exception {
        if (b){
            for (Table table : tables) {
                File oldDir = new File (dest.getAbsolutePath ().replace ("[Table2]", table.getCamel_name ()));
                FileUtil.mkdir (oldDir);
                for (File file : files) {
                    File newFile = new File (oldDir, file.getName ());
                    copyFile(file,newFile,isOverride,tables);
                }
            }
        }else{
            for (File file : files) {
                File newFile = new File (dest, file.getName ());
                copyFile(file,newFile,isOverride,tables);
            }
        }
    }
    /**
     * 生成空文件夹
     * @param dest
     * @param b
     * @param tables
     */
    private void emptyDir(File dest, boolean b, List<Table> tables) {
        if (b){
            for (Table table : tables) {
                FileUtil.mkdir (dest.getAbsolutePath ().replace ("[Table2]",table.getCamel_name ()));
            }
        }else{
            FileUtil.mkdir (dest);
        }
    }
    /**
     * 生成文件
     * @param src
     * @param dest
     * @param isOverride
     * @param b
     * @param tables
     */
    private void writeContent(File src, File dest, boolean isOverride, boolean b, List<Table> tables) {
        if (b){
            for (Table table : tables) {
                File file = FileUtil.copy (src, dest, isOverride);
                file.renameTo (new File (file.getAbsolutePath ().replace ("[Table2]",table.getCamel_name ())));
            }
        }else{
            File file = FileUtil.copy (src, dest, isOverride);
        }
    }

}
