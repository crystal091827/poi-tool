package com.common.poi.excel.validator.title.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.common.poi.excel.constant.FileType;
import com.common.poi.excel.model.FileModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @comment Excel2007文件标题校验器
 */
public class Excel2007TitleValidator extends ExcelTitleValidator {

	/**
	 * @param file
	 * @param titleList
	 */
	public Excel2007TitleValidator(File file, List<String> titleList, FileModel model) {
		super(file, titleList,model);
	}

	@Override
	protected Workbook getWorkbook() {
		Workbook book = null;
		try {
			book = new XSSFWorkbook(this.in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

}

