package com.github.wb322.entity;

/**
 * @author wubo
 * @date 2019年11月18日 9:39
 */
public class Setting {
    private String name;
    private String value;

    public Setting(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public Setting() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
