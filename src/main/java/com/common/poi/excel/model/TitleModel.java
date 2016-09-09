package com.common.poi.excel.model;

/**
 * Created by Luopc on 2016-9-8-0008.
 */
public class TitleModel {

    private boolean nullAble;

    private String titleCode;

    private String titleName;

    private String titleType;

    private int titleLength;

    private String titleRule;

    private String errorTip;

    public boolean isNullAble() {
        return nullAble;
    }

    public void setNullAble(boolean nullAble) {
        this.nullAble = nullAble;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public int getTitleLength() {
        return titleLength;
    }

    public void setTitleLength(int titleLength) {
        this.titleLength = titleLength;
    }

    public String getTitleRule() {
        return titleRule;
    }

    public void setTitleRule(String titleRule) {
        this.titleRule = titleRule;
    }
}
