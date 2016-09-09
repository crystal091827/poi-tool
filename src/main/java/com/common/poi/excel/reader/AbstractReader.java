package com.common.poi.excel.reader;

import com.common.poi.excel.model.FileModel;

import java.io.File;
import java.util.Map;

public abstract class AbstractReader implements Reader{

	protected static final String NOT_NULL_PROPERTY_IS_NULL = "非空列为空";
	
	protected static final String NOT_CURRECT_DATA_TYPE = "非正确的数据类型";
	
	protected File file;
	
	/**
	 * KEY ：title
	 * VALUE : property
	 */
	protected Map<String, String> propMap = null;

	protected FileModel fileModel;

	public AbstractReader(File file, Map<String, String> propMap ,FileModel fileModel) {
		super();
		this.file = file;
		this.propMap = propMap;
		this.fileModel = fileModel;
	}
}

