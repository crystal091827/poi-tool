package com.common.poi.excel.validator.data;

import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.model.CellValueModel;
import com.common.poi.excel.util.CellModelSearcher;
import com.common.poi.excel.util.StringUtil;
import com.common.poi.excel.model.FileModel;

import java.util.Map;


/**
 *
 * @comment关键性为空校验器
 */
public class NullKeyValidator extends AbstractValidator {

	/**
	 * @param model
	 */
	public NullKeyValidator(FileModel model) {
		super(model);
		this.errorTypeName = "非空项为空";
	}

	@Override
	public boolean validate(Map<String, CellValueModel> rowData) {
		boolean ok = true;
		for(String propName : rowData.keySet()){
			CellModel cellModel = CellModelSearcher.getCellModelByPropName(propName, this.model);
			if(!cellModel.isNullAble() && this.isNull(rowData.get(propName))){
				ok = false;
				break;
			}
		}
		return ok;
	}
	
	private boolean isNull(CellValueModel val){
		return val == null ? true : (val.getVal() == null ? true : StringUtil.isEmpty(val.getVal().toString()));
	}
}

