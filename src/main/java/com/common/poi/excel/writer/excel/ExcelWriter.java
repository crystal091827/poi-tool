package com.common.poi.excel.writer.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.common.poi.excel.constant.CellType;
import com.common.poi.excel.converter.DataPropGetter;
import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.util.FileCreator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.common.poi.excel.exception.R2D4Exception;
import com.common.poi.excel.model.FileModel;
import com.common.poi.excel.writer.AbstractWriter;

public abstract class ExcelWriter extends AbstractWriter{

	public ExcelWriter(List<?> data, String fileName, FileModel model){
		super(data, fileName, model);
	}
	
	protected abstract Workbook getWorkbook();

	@Override
	public File write(){
		Workbook book = this.getWorkbook();
		int max = 65530;
		
		Sheet sheet = null;
		List<CellModel> title = model.getRow().getCells();
		
		for(int i = 0, len = data.size(); i <= len; i++){
			if(i % max == 0){
				sheet = book.createSheet();
				this.createTitle(sheet, title);
			}
			if(i == len){
				break;
			}
			this.appendRow(sheet, title, data.get(i), i + 1);
		}
		
		File file = null;
		FileOutputStream out = null;
		try{
			file = FileCreator.create(this.fileName);
			out = new FileOutputStream(file);
			book.write(out);
		}catch(Exception e){
			throw new R2D4Exception("创建导出文件时出错");
		}finally{
			if(out != null){
				try {out.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
		
		return file;
	}
	
	private void createTitle(Sheet sheet, List<CellModel> title){
		Row row = sheet.createRow(0);
		for(int i = 0, len = title.size(); i < len; i++){
			Cell cell = row.createCell(i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(title.get(i).getTitle());
		}
	}
	
	private void appendRow(Sheet sheet, List<CellModel> title, Object data, int rowNum){
		Row row = sheet.createRow(rowNum);
		for(int i = 0, len = title.size(); i < len; i++){
			Cell cell = row.createCell(i);
			CellModel model = title.get(i);
			Object val = DataPropGetter.getPropVal(model.getProperty(), data);
			if(model.getType().equals(CellType.STRING)){
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if(val != null){
					cell.setCellValue(val.toString());
				}
			}else{
				cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				if(val == null){
					continue;
				}
				CellType type = model.getType();
				if(type.equals(CellType.INTEGER)){
					cell.setCellValue(Integer.parseInt(val.toString()));
				}else if(type.equals(CellType.LONG)){
					cell.setCellValue(Long.parseLong(val.toString()));
				}else if(type.equals(CellType.FLOAT)){
					cell.setCellValue(Float.parseFloat(val.toString()));
				}else if(type.equals(CellType.DOUBLE)
						|| type.equals(CellType.NUMBERIC)){
					cell.setCellValue(Double.parseDouble(val.toString()));
				}
			}
		}
	}
}

