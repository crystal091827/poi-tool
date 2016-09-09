package com.common.poi.excel.validator.title;

import java.io.File;
import java.util.List;

/**
 * @comment 标题行校验类
 */
public abstract class TitleValidator{
	
	private static final String EMPTY = "";
	
	/**
	 * 要校验的文件
	 */
	protected File file;
	
	/**
	 * 标题
	 */
	protected List<String> titleList;
	
	protected boolean valid = true;
	
	protected String info;
	
	public TitleValidator(File file, List<String> titleList){
		this.file = file;
		this.titleList = titleList;
	}
	
	
	/**
	 * 标题校验方法
	 */
	protected abstract void validate();
	
	public boolean isValid(){
		return this.valid;
	}
	
	public String getInvalidInfo(){
		return this.valid ? EMPTY :this.info;
	}

}

