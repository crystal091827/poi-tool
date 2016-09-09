package com.common.poi.excel.reader;

import java.util.List;
import java.util.Map;

import com.common.poi.excel.model.CellValueModel;


public interface Reader {

	
	/**
	 * 读取数据接口。读取到的数据用List嵌套Map的方式返回
	 * List中每个Map是一行数据
	 * Map中      Key 是String类型，是导入对象的属性名称
	 * 		Value 是CellValueModel，是导入值的一个对象。
	 * @return
	 */
	public List<Map<String, CellValueModel>> read();
}

