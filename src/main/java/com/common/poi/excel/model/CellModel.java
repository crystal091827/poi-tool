package com.common.poi.excel.model;

import com.common.poi.excel.constant.CellType;

public class CellModel {

    /**
     * 是否可以为空: true 表示可以为空
     */
    private boolean nullAble;
    /**
     * 属性名称
     */
    private String property;
    /**
     * 标题
     */
    private String title;
    /**
     * 类型提示
     */
    public String tip = "Error";
    /**
     * 类型
     */
    public CellType type;


    public boolean isNullAble() {
        return nullAble;
    }

    public void setNullAble(boolean nullAble) {
        this.nullAble = nullAble;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}