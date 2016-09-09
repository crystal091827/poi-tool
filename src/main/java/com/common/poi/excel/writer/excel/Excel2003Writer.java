package com.common.poi.excel.writer.excel;

import java.util.List;

import com.common.poi.excel.model.FileModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class Excel2003Writer extends ExcelWriter {

	/**
	 * @param data
	 * @param fileName
	 * @param model
	 */
	public Excel2003Writer(List<?> data, String fileName, FileModel model) {
		super(data, fileName, model);
	}

	@Override
	protected Workbook getWorkbook() {
		return new HSSFWorkbook();
	}

}

