package com.github.wb322.entity;

/**
 * @author wubo
 * @date 2019年11月12日 10:56
 */
public class Template {
    private Integer id;
    private String name;
    private Integer pId;
    private boolean isParent;

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public Template() {
    }

    public Template(Integer id, String name, Integer pId,boolean isParent) {
        this.id = id;
        this.name = name;
        this.pId = pId;
        this.isParent = isParent;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
