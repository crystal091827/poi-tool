package com.common.poi.excel.reader.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.model.FileModel;

public class TitlePropUtil {
	
	
	private TitlePropUtil() {}


	public static Map<String, String> initPropMaps(FileModel model){
		Map<String, String> propMap = new HashMap<String, String>();
		List<CellModel> cells = model.getRow().getCells();
		for(CellModel cell : cells){
			String title = cell.getTitle();
			String property = cell.getProperty();
			propMap.put(title, property);
		}
		return propMap;
	}
}

