package com.common.poi.excel.model;

import java.util.List;


public class RowModel {

	/**
	 * 单元格模型
	 */
	private List<CellModel> cells;

	public List<CellModel> getCells() {
		return cells;
	}

	public void setCells(List<CellModel> cells) {
		this.cells = cells;
	}
}