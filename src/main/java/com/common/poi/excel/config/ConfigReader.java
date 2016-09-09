package com.common.poi.excel.config;

import com.common.poi.excel.constant.CellType;
import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.model.FileModel;
import com.common.poi.excel.model.RowModel;
import com.common.poi.excel.model.TitleModel;
import com.google.common.collect.Lists;

import java.util.List;

public class ConfigReader {
	
	private ConfigReader(){}


	public static FileModel getFileModel(String modeId){
		List<TitleModel> titleList = testTitle();

		return getFileModel(modeId,titleList);
	}

	public static FileModel getFileModel(String modeId ,List<TitleModel> titleList){
		FileModel fileModel = new FileModel();
		List<CellModel> cells = Lists.newArrayList();
		for (TitleModel title : titleList ) {
			CellModel cell = new CellModel();
			boolean nullAble = title.isNullAble();
			String property = title.getTitleCode();
			String name = title.getTitleName();
			String mark = title.getTitleType();
			cell.setNullAble(Boolean.valueOf(nullAble));
			cell.setProperty(property);
			cell.setTitle(name);
			cell.setType(CellType.get(mark));
			cells.add(cell);
		}
		RowModel row = new RowModel();
		row.setCells(cells);
		fileModel.setTitleList(titleList);
		fileModel.setId(modeId);
		fileModel.setRow(row);
		return fileModel;
	}


	private static List<TitleModel> testTitle(){
		List<TitleModel> titleList = Lists.newArrayList();
		TitleModel title1 = new TitleModel();
		TitleModel title2 = new TitleModel();
		TitleModel title3 = new TitleModel();
		TitleModel title4 = new TitleModel();

		title1.setNullAble(true);
		title2.setNullAble(true);
		title3.setNullAble(true);
		title4.setNullAble(true);

		title1.setTitleCode("XM");
		title1.setTitleName("姓名");
		title1.setTitleType("number");
		title1.setTitleLength(100);


		title2.setTitleCode("NL");
		title2.setTitleName("年龄");
		title2.setTitleType("varchar");
		title2.setTitleLength(100);

		title3.setTitleCode("CSRQ");
		title3.setTitleName("出生日期");
		title3.setTitleType("varchar");
		title3.setTitleLength(100);

		title4.setTitleCode("JTDZ");
		title4.setTitleName("家庭地址");
		title4.setTitleType("varchar");
		title4.setTitleLength(100);


		titleList.add(title1);
		titleList.add(title2);
		titleList.add(title3);
		titleList.add(title4);
		return titleList;
	}

}

