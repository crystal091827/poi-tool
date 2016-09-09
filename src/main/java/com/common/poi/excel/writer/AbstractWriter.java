package com.common.poi.excel.writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.util.CollectionUtil;
import com.common.poi.excel.model.FileModel;

public abstract class AbstractWriter implements Writer {

	protected String fileName;
	
	protected FileModel model;
	
	protected List<?> data;
	
	/**
	 * KEY ：title
	 * VALUE : property
	 */
	protected Map<String, String> propMap = null;
	/**
	 * KEY : property name
	 * VALUE : CellType
	 */
	protected Map<String, CellModel> propTypeMap = null;
	
	public AbstractWriter(List<?> data, String fileName, FileModel model) {
		super();
		this.model = model;
		this.data = CollectionUtil.isEmpty(data) ? new ArrayList<Object>() : data;
		this.fileName = fileName;
		this.initPropMaps();
	}

	private void initPropMaps(){
		this.propMap = new HashMap<String, String>();
		this.propTypeMap = new HashMap<String, CellModel>();
		List<CellModel> cells = this.model.getRow().getCells();
		for(CellModel cell : cells){
			String title = cell.getTitle();
			String property = cell.getProperty();
			this.propMap.put(title, property);
			this.propTypeMap.put(property, cell);
		}
	}
}

