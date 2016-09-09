package com.common.poi.excel.model;


import java.util.List;

public class FileModel {


    /**
     * ModelID
     */
    private String id;
    /**
     * sheetName
     */
    private String sheetName;
    /**
     * 表格名称
     */
    private String title;
    /**
     * 表格标题第几行
     */
    private int titleRows = 0;
    /**
     * 表格高度
     */
    private short titleHeight = 20;
    /**
     * 表格数据行高度
     */
    private short headHeight = 8;
    /**
     * 表头是第几行
     */
    private int headRows = 1;
    /**
     * 模板表头
     */
    private List<TitleModel> titleList;
    /**
     * 表真正的数据开始于第几行
     */
    private int dataRows = 2;

    /**
     * 行模型
     */
    private RowModel row;


    public FileModel() {

    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public RowModel getRow() {
        return row;
    }


    public void setRow(RowModel row) {
        this.row = row;
    }

    public List<TitleModel> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<TitleModel> titleList) {
        this.titleList = titleList;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleRows() {
        return titleRows;
    }

    public void setTitleRows(int titleRows) {
        this.titleRows = titleRows;
    }

    public short getTitleHeight() {
        return titleHeight;
    }

    public void setTitleHeight(short titleHeight) {
        this.titleHeight = titleHeight;
    }

    public short getHeadHeight() {
        return headHeight;
    }

    public void setHeadHeight(short headHeight) {
        this.headHeight = headHeight;
    }

    public int getHeadRows() {
        return headRows;
    }

    public void setHeadRows(int headRows) {
        this.headRows = headRows;
    }

    public int getDataRows() {
        return dataRows;
    }

    public void setDataRows(int dataRows) {
        this.dataRows = dataRows;
    }
}