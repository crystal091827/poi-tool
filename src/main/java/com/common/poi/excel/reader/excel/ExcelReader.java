package com.common.poi.excel.reader.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.poi.excel.model.CellValueModel;
import com.common.poi.excel.model.FileModel;
import com.common.poi.excel.reader.AbstractReader;
import com.common.poi.excel.util.StringUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.common.poi.excel.exception.R2D4Exception;

public abstract class ExcelReader extends AbstractReader {

	
	private List<Map<String, CellValueModel>> datas;
	
	protected InputStream in;
	/**
	 * @param file
	 * @param model
	 */
	public ExcelReader(File file, Map<String, String> propMap,FileModel model) {
		super(file, propMap ,model);
		this.datas = new ArrayList<Map<String, CellValueModel>>();
	}

	@Override
	public List<Map<String, CellValueModel>> read() {
		try {
			this.in = new FileInputStream(this.file);
			Workbook book = this.getWorkbook();
			this.readSheet(book.getSheetAt(0));
		} catch (Exception e) {
			e.printStackTrace();
			throw new R2D4Exception(e.getMessage());
		}finally{
			if(this.in != null){
				try {this.in.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
		
		return this.datas;
	}
	
	
	/**
	 * 读取一个Sheet的方法
	 * @param sheet
	 * @return
	 */
	private void readSheet(Sheet sheet){
		Map<Integer, String> propIndexMap = this.createPropertyIndex(sheet);
		int lastRow = sheet.getLastRowNum();
		for(int i = fileModel.getDataRows(); i <= lastRow; i++){
			Row row = sheet.getRow(i);
			if(!this.isEmptyRow(row)){
				this.datas.add(this.readRow(row, propIndexMap));
			}
		}
	}
	
	/**
	 * 从Row中读取一条记录
	 * @param row
	 * @return
	 */
	private Map<String, CellValueModel> readRow(Row row, Map<Integer, String> propIndexMap){
		Map<String, CellValueModel> rowData = new HashMap<String, CellValueModel>();
		for(Integer index : propIndexMap.keySet()){
			Cell cell = row.getCell(index);
			String propertyName = propIndexMap.get(index);
			CellValueModel cellVal =  this.getCellValue(cell);
			rowData.put(propertyName, cellVal);
		}
		return rowData;
	}
	
	private CellValueModel getCellValue(Cell cell){
		CellValueModel res = null;
		if(cell == null){
			res = new CellValueModel(null, Cell.CELL_TYPE_BLANK);
		}else{
			int type = cell.getCellType();
			switch(type){
				case Cell.CELL_TYPE_BLANK: 
					res = new CellValueModel(null, Cell.CELL_TYPE_BLANK);
					break;
				case Cell.CELL_TYPE_NUMERIC:
					res = new CellValueModel(cell,cell.getNumericCellValue(),Cell.CELL_TYPE_NUMERIC);
					break;
				case Cell.CELL_TYPE_STRING:
					res = new CellValueModel(cell,cell.getStringCellValue(),Cell.CELL_TYPE_STRING);
					break;
			}
		}
		
		return res;
	}
	
	private boolean isEmptyRow(Row row){
		if(row == null || row.getLastCellNum() == -1){
			return true;
		}
		
		boolean empty = true;
		
		int last = row.getLastCellNum();
		for(int i = 0; i < last; i++){
			Cell cell = row.getCell(i);
			if(cell == null){
				continue;
			}
			
			CellValueModel val = this.getCellValue(cell);
			
			if(val == null ? true : (val.getVal() == null ? true : StringUtil.isEmpty(val.getVal().toString()))){
				continue;
			}else{
				empty = false;
				break;
			}
		}
		
		return empty;
		
	}
	
	private Map<Integer, String> createPropertyIndex(Sheet sheet){
		Row row = sheet.getRow(fileModel.getHeadRows());
		Map<Integer, String> res = new HashMap<Integer, String>();
		for(int i = 0, len = row.getLastCellNum(); i < len; i++){
			Cell cell = row.getCell(i);
			if(cell == null){
				continue;
			}
			String titleVal = row.getCell(i).getStringCellValue();
			if(StringUtil.isEmpty(titleVal)){
				continue;
			}
			String title = titleVal.trim();
			res.put(i, this.propMap.get(title));
		}
		return res;
	}
	
	protected abstract Workbook getWorkbook();
}

