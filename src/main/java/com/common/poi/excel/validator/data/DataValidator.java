package com.common.poi.excel.validator.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.poi.excel.model.CellValueModel;
import com.common.poi.excel.model.FileModel;

public class DataValidator {

	
	private List<Map<String, CellValueModel>> origData;
	private Map<String, List<Integer>> errorIndexMap;
	private Map<String, List<Map<String,String>>> errorTip;
	private List<Validator> validators;
	private FileModel model;

	public DataValidator(List<Map<String, CellValueModel>> origData, FileModel model) {
		super();
		this.origData = origData;
		this.errorIndexMap = new HashMap<String, List<Integer>>();
		this.errorTip = new HashMap<String, List<Map<String,String>>>();
		this.model = model;
		this.initValidators();
		this.validate();
	}
	
	public Map<String, List<Integer>> getErrorIndexMap() {
		return errorIndexMap;
	}

	public Map<String, List<Map<String, String>>> getErrorTip() {
		return errorTip;
	}

	private void validate(){
		for(int i = 0, len = this.origData.size(); i < len; i++){
			Map<String, CellValueModel> rowData = this.origData.get(i);
			for(Validator v : this.validators){
				if(!v.validate(rowData)){
					String errorTypeName = v.getErrorTypeName();
					List<Map<String,String>> errorInfo = v.getTipList();
					if(!this.errorIndexMap.containsKey(errorTypeName)){
						this.errorTip.put(errorTypeName, errorInfo);
						this.errorIndexMap.put(errorTypeName, new ArrayList<Integer>());
					}
					this.errorIndexMap.get(errorTypeName).add(i);
				}
			}
		}
	}
	
	
	private void initValidators(){
		this.validators = new ArrayList<Validator>();
		//在这里校验非空
		this.validators.add(new NullKeyValidator(this.model));
		//在这里校验Excel内类型是否和model一致
		this.validators.add(new TypeValidator(this.model));
	}
}

