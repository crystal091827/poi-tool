package com.common.poi.excel.reader.excel;

import java.io.File;
import java.util.Map;

import com.common.poi.excel.model.FileModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.common.poi.excel.exception.R2D4Exception;

public class Excel2007Reader extends ExcelReader {

	

	/**
	 * @param file
	 * @param model
	 */
	public Excel2007Reader(File file, Map<String, String> propMap,FileModel model) {
		super(file, propMap , model);
	}

	@Override
	protected Workbook getWorkbook() {
		Workbook book = null;
		try {
			book = new XSSFWorkbook(this.in);
		} catch (Exception e) {
			throw new R2D4Exception(e.getMessage());
		}
		return book;
	}

}

