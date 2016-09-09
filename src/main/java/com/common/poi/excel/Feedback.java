package com.common.poi.excel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.common.poi.excel.model.CellValueModel;

public class Feedback implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1099487662498409932L;
	private String titleErrorInfo;
	private boolean titleError;
	private boolean error;
	private Map<String, List<Map<String, CellValueModel>>> errorData;
	private Map<String, List<Map<String,String>>> errorTip;
	private List<Map<String,Object>> result;
	
	public Feedback(boolean titleError, String titleErrorInfo) {
		super();
		this.titleErrorInfo = titleErrorInfo;
		this.titleError = titleError;
	}

	public Feedback(boolean error, Map<String, List<Map<String, CellValueModel>>> errorData,
			List<Map<String,Object>> result) {
		super();
		this.error = error;
		this.errorData = errorData;
		this.result = result;
	}

	public Feedback(boolean error, Map<String, List<Map<String, CellValueModel>>> errorData,
					List<Map<String,Object>> result,Map<String, List<Map<String,String>>> errorTip) {
		super();
		this.error = error;
		this.errorData = errorData;
		this.result = result;
		this.errorTip = errorTip;
	}

	/**
	 * 标识是否生成了错误数据
	 * @return
	 */
	public boolean hasError() {
		return error;
	}
	
	/**
	 * 返回错误数据
	 * @return
	 */
	public Map<String, List<Map<String, CellValueModel>>> getErrorData() {
		return errorData;
	}

	/**
	 * 返回正确的数据
	 * @return
	 */
	public List<Map<String,Object>> getResult() {
		return result;
	}

	/**
	 * 返回标题行错误信息
	 * @return
	 */
	public String getTitleErrorInfo() {
		return titleErrorInfo;
	}

	/**
	 * 返回标题行是否有错
	 * @return
	 */
	public boolean hasTitleError() {
		return titleError;
	}

	/**
	 * 返回错误的提示信息
	 * @return
	 */
	public Map<String, List<Map<String, String>>> getErrorTip() {
		return errorTip;
	}

}

