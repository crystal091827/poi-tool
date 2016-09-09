package com.common.poi.excel.validator.config.model;

/**
 * @comment 属性配置
 */
public class AttrConfig {
	
	/**
	 * 数量限制
	 */
	private String quantity;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 取值范围
	 */
	private String[] range;
	
	public AttrConfig(String name, String quantity, String[] range) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.range = range;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getRange() {
		return range;
	}
	
	public String getQuantity() {
		return quantity;
	}
}

