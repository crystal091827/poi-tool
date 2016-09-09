package com.common.poi.excel.reader.excel;

import java.io.File;
import java.util.Map;

import com.common.poi.excel.exception.R2D4Exception;
import com.common.poi.excel.model.FileModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class Excel2003Reader extends ExcelReader {


	/**
	 * @param file
	 * @param propMap
	 */
	public Excel2003Reader(File file, Map<String, String> propMap,FileModel model) {
		super(file, propMap,model);
	}

	@Override
	protected Workbook getWorkbook() {
		Workbook book = null;
		try {
			book = new HSSFWorkbook(this.in);
		} catch (Exception e) {
			throw new R2D4Exception(e.getMessage());
		}
		return book;
	}


}

