package com.common.poi.excel.model;

import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.aggregates.FormulaRecordAggregate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;

import java.io.Serializable;
import java.util.Date;

public class CellValueModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6008231264138041913L;

	private Cell cell;

	/**
	 * 单元格的值
	 */
	private Object val;
	
	/**
	 * 单元格的数据类型,这里用int是因为目前只支持Excel格式
	 * 所以这个int值就是Excel的单元格类型,
	 * 后续如果有支持其他文件格式的时候，再考虑做扩展
	 */
	private int cellType;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}

	public int getCellType() {
		return cellType;
	}

	public void setCellType(int cellType) {
		this.cellType = cellType;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public CellValueModel(Object val, int cellType) {
		super();
		this.val = val;
		this.cellType = cellType;
	}


	public CellValueModel(Cell cell,Object val, int cellType) {
		super();
		this.val = val;
		this.cell = cell;
		this.cellType = cellType;
	}

	public double getNumericCellValue(){
		return cell.getNumericCellValue();
	}

	public Date getDateCellValue(){
		return cell.getDateCellValue();
	}

	public RichTextString getRichStringCellValue(){
		return cell.getRichStringCellValue();
	}

	public String getStringCellValue(){
		return cell.getStringCellValue();
	}

	public Boolean getBooleanCellValue(){
		return cell.getBooleanCellValue();
	}

	public String getCellFormula(){
		return cell.getCellFormula();
	}
}

