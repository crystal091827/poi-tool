package com.common.poi.excel.util;

import com.common.poi.excel.constant.CellType;
import com.common.poi.excel.constant.CellValueServer;
import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.model.CellValueModel;
import com.common.poi.excel.model.FileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @comment 根据错误信息的Index，把错误信息整理出来的工具类
 * 主要提供的功能有，根据错误信息下标，得到错误信息不同类型的集合，放在Map里。
 * 然后把数据中的错误数据都去掉，剩下一个正确数据的集合
 */
public class SeperateDataUtil {

	
	private Map<String, List<Integer>> errorIndexMap;
	
	private List<Map<String, CellValueModel>> origData;
	
	private Map<String, List<Map<String, CellValueModel>>> errorData;
	
	private List<Integer> errorIndex;
	
	private List<Map<String, Object>> correctData;
	
	private FileModel model;
 
	public SeperateDataUtil(Map<String, List<Integer>> errorIndexMap,
			List<Map<String, CellValueModel>> origData, FileModel model) {
		super();
		this.errorIndexMap = errorIndexMap;
		this.origData = origData;
		this.errorData = new HashMap<String, List<Map<String, CellValueModel>>>();
		this.errorIndex = new ArrayList<Integer>();
		this.correctData = new ArrayList<Map<String, Object>>();
		this.model = model;
		this.seperateErrorData();
		this.fillErrorIndexList();
		this.genCorrectData();
	}
	
	public List<Map<String,Object>> getCorrectData(){
		return this.correctData;
	}
	
	public Map<String, List<Map<String,CellValueModel>>> getErrorData(){
		return this.errorData;
	}
	
	private void seperateErrorData(){
		for(String errorTypeName : this.errorIndexMap.keySet()){
			List<Integer> indexes = this.errorIndexMap.get(errorTypeName);
			if(CollectionUtil.isEmpty(indexes)){
				continue;
			}
			
			List<Map<String, CellValueModel>> list = new ArrayList<Map<String, CellValueModel>>();
			for(int index  : indexes){
				list.add(this.origData.get(index));
			}
			this.errorData.put(errorTypeName, list);
		}
	}
	
	private void fillErrorIndexList(){
		for(String errorTypeName : this.errorIndexMap.keySet()){
			List<Integer> indexes = this.errorIndexMap.get(errorTypeName);
			for(int index  : indexes){
				if(!this.errorIndex.contains(index)){
					this.errorIndex.add(index);
				}
			}
		}
	}
	
	private void genCorrectData(){
		for(int i = 0, len = this.origData.size(); i < len; i++){
			if(this.errorIndex.contains(i)){
				continue;
			}else{
				this.correctData.add(this.genCorrectData(this.origData.get(i)));
			}
		}
	}

	/**
	 * 根据Model读取Excel中的值
	 * @param rowData
	 * @return
	 */
	private Map<String, Object> genCorrectData(Map<String, CellValueModel> rowData){
		Map<String, Object> data = new HashMap<String, Object>();
		for(String propName : rowData.keySet()){
			CellValueModel valueModel = rowData.get(propName);
			CellModel cellModel = CellModelSearcher.getCellModelByPropName(propName, model);
			Object val = this.getValue(valueModel, cellModel);
			data.put(propName, val);
		}
		return data;
	}


	/**
	 *
	 * @param valueModel Exce中的值
	 * @param cellModel Model
	 * @return
	 */
	private Object getValue(CellValueModel valueModel, CellModel cellModel){

		int code = cellModel.getType().getCode();
		Object val = null;
		switch(code){
			case CellType.TYPE_DOUBLE:
				val = CellValueServer.getDoubledata(valueModel);
				break;
			case CellType.TYPE_FLOAT:
				val = CellValueServer.getFloatData(valueModel);
				break;
			case CellType.TYPE_INTEGER:
				val = CellValueServer.getIntegerData(valueModel);
				break;
			case CellType.TYPE_LONG:
				val = CellValueServer.getLongData(valueModel);
				break;
			case CellType.TYPE_NUMBERIC:
				val = CellValueServer.getDoubledata(valueModel);
				break;
			case CellType.TYPE_PERCENTAGE:
				val = CellValueServer.getDoubledata(valueModel);
				break;
			case CellType.TYPE_DATE:
				val = CellValueServer.getDateData(valueModel);
				break;
			case CellType.TYPE_TIME:
				val = CellValueServer.getDateData(valueModel);
				break;
			case CellType.TYPE_DATE_TIME:
				val = CellValueServer.getDateData(valueModel);
				break;
			case CellType.TYPE_STRING:
				val = CellValueServer.getStringData(valueModel);
				break;
		}
		return val;
	}

}

