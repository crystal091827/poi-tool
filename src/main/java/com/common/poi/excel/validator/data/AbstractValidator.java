package com.common.poi.excel.validator.data;

import com.common.poi.excel.model.FileModel;

import java.util.List;
import java.util.Map;

public abstract class AbstractValidator implements Validator {
	
	protected FileModel model;
	
	protected String errorTypeName;

	protected int errorColumn;//列

	protected int errorRow;//行

	protected String errorTip;//错误提示

	protected List<Map<String,String>>  tipList ;

	public AbstractValidator(FileModel model) {
		super();
		this.model = model;
	}
	
	
	@Override
	public String getErrorTypeName() {
		return this.errorTypeName;
	}
	@Override
	public List<Map<String, String>> getTipList() {
		return tipList;
	}


	public void setErrorTypeName(String errorTypeName) {
		this.errorTypeName = errorTypeName;
	}

	public String getErrorTip() {
		return errorTip;
	}

	public void setErrorTip(String errorTip) {
		this.errorTip = errorTip;
	}

	public int getErrorColumn() {
		return errorColumn;
	}

	public void setErrorColumn(int errorColumn) {
		this.errorColumn = errorColumn;
	}

	public int getErrorRow() {
		return errorRow;
	}

	public void setErrorRow(int errorRow) {
		this.errorRow = errorRow;
	}


}

