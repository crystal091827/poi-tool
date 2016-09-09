package com.common.poi.excel.validator.config.model;

public class FileConfig {

	
	private TagConfig rootConfig;

	public FileConfig(TagConfig rootConfig) {
		super();
		this.rootConfig = rootConfig;
	}

	public TagConfig getRootConfig() {
		return rootConfig;
	}
}

