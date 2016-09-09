package com.common.poi.excel.validator.title.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.common.poi.excel.model.FileModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @comment Excel2003文件标题校验器
 */
public class Excel2003TitleValidator extends ExcelTitleValidator {

	/**
	 * @param file
	 * @param titleList
	 */
	public Excel2003TitleValidator(File file, List<String> titleList,FileModel model) {
		super(file, titleList,model);
	}

	@Override
	protected Workbook getWorkbook() {
		Workbook book = null;
		try {
			book = new HSSFWorkbook(this.in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return book;
	}

}

