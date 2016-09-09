package com.common.poi.excel.validator.data;

import java.util.List;
import java.util.Map;

import com.common.poi.excel.model.CellValueModel;

public interface Validator {

	
	/**
	 * 校验行数据方法
	 * @param rowData
	 * @return
	 */
	public boolean validate(Map<String, CellValueModel> rowData);
	
	/**
	 * 得到错误类型名称
	 * @return
	 */
	public String getErrorTypeName();

	/**
	 * 错误的提示信息集合
	 * @return
	 */
	List<Map<String,String>> getTipList();
}

