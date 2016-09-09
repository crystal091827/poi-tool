package com.common.poi.excel.validator.config.model;

import java.util.List;

public class TagConfig {
	/**
	 * 数量限制
	 */
	private String quantity;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 子标签
	 */
	private List<TagConfig> subTags;
	
	/**
	 * 属性
	 */
	private List<AttrConfig> attrs;
	
	
	public TagConfig(String name,
			String quantity,
			List<TagConfig> subTags,
			List<AttrConfig> attrs) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.subTags = subTags;
		this.attrs = attrs;
	}
	
	public String getName() {
		return name;
	}
	
	public List<TagConfig> getSubTags() {
		return subTags;
	}
	
	public List<AttrConfig> getAttrs() {
		return attrs;
	}
	public String getQuantity() {
		return quantity;
	}
}

