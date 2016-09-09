package com.common.poi.excel.util;

import java.util.List;

import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.model.FileModel;

/**
 * @comment在一个CellModel的List中根据propName找到CellModel
 */
public class CellModelSearcher {

	public static CellModel getCellModelByPropName(String propName, FileModel model){
		CellModel res = null;
		List<CellModel> cellModels = model.getRow().getCells();
		for(int i = 0, len = cellModels.size();i < len; i++){
			CellModel cellModel = cellModels.get(i);
			if(cellModel.getProperty().equals(propName)){
				res = cellModel;
				break;
			}
		}
		return res;
	}
}

