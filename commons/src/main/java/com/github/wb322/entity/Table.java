package com.github.wb322.entity;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * @author wubo
 * @date 2019年11月11日 10:02
 */
public class Table {
    /**
     * 表名
     */
    private String name;

    /**
     * 驼峰格式表名
     */
    private String camel_name;
    /**
     * 主键
     */
    private String pkey;
    /**
     * 驼峰格式主键
     */
    private String camel_pkey;
    /**
     * 主键类型
     */
    private String pkeyType;
    /**
     * 表引擎
     */
    private String engine;
    /**
     * 表说明
     */
    private String comment;
    /**
     * 表的创建时间
     */
    private Date createTime;
    /**
     * 列集合
     */
    private List<TableColumn> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCamel_name() {
        return camel_name;
    }

    public void setCamel_name(String camel_name) {
        this.camel_name = camel_name;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public String getCamel_pkey() {
        return camel_pkey;
    }

    public void setCamel_pkey(String camel_pkey) {
        this.camel_pkey = camel_pkey;
    }

    public String getPkeyType() {
        return pkeyType;
    }

    public void setPkeyType(String pkeyType) {
        this.pkeyType = pkeyType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateTime() {
        return DateUtil.formatDateTime (this.createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }
}

