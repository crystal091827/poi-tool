package com.common.poi.excel.util;

import com.common.poi.excel.config.ConfigReader;
import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.model.CellValueModel;
import com.common.poi.excel.model.FileModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @comment 错误数据文件生成器 
 */
public class ErrorFileCreator {

	private static final String subFix = ".xls";
	
	public static Map<String, File> create(String id, Map<String, List<Map<String,CellValueModel>>> errorData){
		FileModel model = ConfigReader.getFileModel(id);
		String uid = UUID.randomUUID().toString();
		Map<String, File> errorFileMap = new HashMap<String, File>();
		for(String errorTypeName : errorData.keySet()){
			errorFileMap.put(errorTypeName, createFile(uid + errorTypeName + subFix, errorData.get(errorTypeName), model));
		}
		return errorFileMap;
	}
	
	
	private static File createFile(String fileName, List<Map<String,CellValueModel>> fileData, FileModel model){
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet();
		appendTitle(sheet, model);
		for(int i = 0, len = fileData.size(); i < len; i++){
			Row row = sheet.createRow(i + 1);
			appendData(row, fileData.get(i), model);
		}
		File file = FileCreator.create(fileName);
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			book.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {out.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
		return file;
	}
	
	
	private static void appendTitle(Sheet sheet, FileModel model){
		Row row = sheet.createRow(0);
		List<CellModel> cellModels = model.getRow().getCells();
		for(int i = 0, len = cellModels.size(); i < len; i++){
			CellModel cellModel = cellModels.get(i);
			String title = cellModel.getTitle();
			Cell cell = row.createCell(i);
			cell.setCellValue(title);
		}
	}
	
	private static void appendData(Row row, Map<String, CellValueModel> rowData, FileModel model){
		List<CellModel> cellModels = model.getRow().getCells();
		for(int i = 0, len = cellModels.size(); i < len; i++){
			CellModel cellModel = cellModels.get(i);
			String propName = cellModel.getProperty();
			CellValueModel val = rowData.get(propName);
			Cell cell = row.createCell(i);
			if(val == null || val.getVal() == null){
				continue;
			}
			if(val.getCellType() == Cell.CELL_TYPE_STRING){
				cell.setCellValue(val.getVal().toString());
			}else if(val.getCellType() == Cell.CELL_TYPE_NUMERIC){
				cell.setCellValue((Double)val.getVal());
			}
		}
		
	}
}

