package com.common.poi.excel.writer.excel;

import java.util.List;

import com.common.poi.excel.model.FileModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel2007Writer extends ExcelWriter {

	/**
	 * @param data
	 * @param fileName
	 * @param model
	 */
	public Excel2007Writer(List<?> data, String fileName, FileModel model) {
		super(data, fileName, model);
	}

	@Override
	protected Workbook getWorkbook() {
		return new XSSFWorkbook();
	}

}

