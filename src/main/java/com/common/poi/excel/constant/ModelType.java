package com.common.poi.excel.constant;

import com.common.poi.excel.exception.R2D4Exception;

public enum ModelType {
	
	
	DEVELOP(1, "开发模式", "develop"),
	DEPLOY(2, "部署模式", "deploy");

	private static final String UNSUPPORT_MODEL = "不支持的工作模式";
	
	/**
	 * 代码
	 */
	private int code;
	/**
	 * 配置标识
	 */
	private String mark;
	/**
	 * 名称
	 */
	private String name;
	
	private ModelType(int code, String mark, String name) {
		this.code = code;
		this.mark = mark;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getMark() {
		return mark;
	}

	public String getName() {
		return name;
	}
	
	
	public static ModelType get(String mark){
		ModelType res = null;
		if("develop".equals(mark)){
			res = DEVELOP;
		}else if("deploy".equals(mark) || null == mark){
			res = DEPLOY;
		}else{
			throw new R2D4Exception(UNSUPPORT_MODEL);
		}
		return res;
	}
}

