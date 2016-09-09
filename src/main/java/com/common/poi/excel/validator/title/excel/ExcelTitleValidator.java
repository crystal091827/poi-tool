package com.common.poi.excel.validator.title.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import com.common.poi.excel.model.FileModel;
import com.common.poi.excel.validator.title.TitleValidator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @comment Excel文件标题校验器
 */
public abstract class ExcelTitleValidator extends TitleValidator {

	private Workbook workbook = null;
	private Sheet sheet = null;
	private Row row = null;
	private Collection<String> durableTitles;
	private Collection<String> unknownTitles;
	private Collection<String> missingTitles;
	protected InputStream in;
	
	/**
	 * @param file
	 * @param titleList
	 */
	public ExcelTitleValidator(File file, List<String> titleList,FileModel model) {
		super(file, titleList);
		try {
			this.durableTitles = new LinkedHashSet<String>();
			this.unknownTitles = new LinkedHashSet<String>();
			this.missingTitles = new ArrayList<String>();
			this.in = new FileInputStream(file);
			this.workbook = this.getWorkbook();
			this.sheet = this.workbook.getSheetAt(0);
			//从设置的表头内读取表头数据
			this.row = this.sheet.getRow(model.getHeadRows());
			this.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(this.in !=  null){
				try {this.in.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
	}
	private static final String EMPTY_TITLE = "Excel文件标题行为空";
	private static final String NOT_VAR_TITLE = "Excel文件标题行只能是文本类型";
	private static final String DURABLE_TITLE = "Excel文件中包含重复的标题: ";
	private static final String UNKNOWN_TITLE = "Excel文件中包含非法的标题：";
	private static final String MISSING_TITLE = "Excel文件中缺少标题：";
	private static final String ENTER = "\n";
	
	@Override
	protected void validate(){
		
		boolean emptyTitle = this.isEmptyTitle();
		boolean allVar = this.isAllVarchar();
		if(emptyTitle == true){
			this.valid = false;
			this.info = EMPTY_TITLE;
			return;
		}
		
		if(allVar == false){
			this.info = NOT_VAR_TITLE;
			this.valid = false;
			return;
		}
		
		StringBuilder builder = new StringBuilder();
		boolean durable = this.hasDurableTitle();
		boolean unknown = this.hasUnknownTitle();
		boolean missing = this.missingTitle();
		boolean ok = true;
		if(durable == true){
			ok = false;
			builder.append(DURABLE_TITLE);
			builder.append(this.durableTitles);
			builder.append(ENTER);
		}
		
		if(unknown == true){
			ok = false;
			builder.append(UNKNOWN_TITLE);
			builder.append(this.unknownTitles);
			builder.append(ENTER);
		}
		
		if(missing == true){
			ok = false;
			builder.append(MISSING_TITLE);
			builder.append(this.missingTitles);
			builder.append(ENTER);
		}
		
		this.valid = ok;
		if(this.valid == false){
			this.info = builder.toString();
		}
	}

	/**
	 * 构造Workbook的方法
	 * @return
	 */
	protected abstract Workbook getWorkbook();

	/**
	 * 判断标题行是否为空
	 * @return
	 */
	private boolean isEmptyTitle(){
		boolean nullRow = this.row == null || this.row.getLastCellNum() == -1;
		boolean allEmpty = true;
		int lastIndex = this.row.getLastCellNum();
		for(int i = 0; i < lastIndex; i++){
			allEmpty = allEmpty && this.isEmptyCell(this.row.getCell(i));
		}
		return nullRow && allEmpty;
	}
	/**
	 * 判断标题行是否全都是字符类型
	 * @return
	 */
	private boolean isAllVarchar(){
		int lastIndex = this.row.getLastCellNum();
		boolean isAllVarchar = true;
		for(int index = 0; index < lastIndex; index++){
			Cell cell = this.row.getCell(index);
			if(this.isEmptyCell(cell)){
				continue;
			}else if(cell.getCellType() != Cell.CELL_TYPE_STRING){
				isAllVarchar = false;
				break;
			}
		}
		return isAllVarchar;
	}
	
	/**
	 * 判断是否有重复的标题列
	 * @return
	 */
	private boolean hasDurableTitle(){
		int lastIndex = this.row.getLastCellNum();
		boolean hasDurable = false;
		for(int i = 0; i < lastIndex; i++){
			Cell cellI = this.row.getCell(i);
			if(this.isEmptyCell(cellI)){
				continue;
			}
			for(int j = i + 1; j <= lastIndex; j++){
				Cell cellJ = this.row.getCell(j);
				if(this.isEmptyCell(cellJ)){
					continue;
				}
				
				String titleI = cellI.getStringCellValue().trim();
				String titleJ = cellJ.getStringCellValue().trim();
				if(titleI.equals(titleJ)){
					hasDurable = true;
					this.durableTitles.add(titleI);
				}
			}
		}
		return hasDurable;
	}
	
	
	/**
	 * 判断是否包含未知的标题列
	 * @return
	 */
	private boolean hasUnknownTitle(){
		boolean hasUnknown = false;
		
		int lastIndex = this.row.getLastCellNum();
		
		for(int i = 0; i < lastIndex; i++){
			Cell cell = this.row.getCell(i);
			if(this.isEmptyCell(cell)){
				continue;
			}else{
				String cellVal = cell.getStringCellValue().trim();
				if(!this.titleList.contains(cellVal)){
					hasUnknown = true;
					this.unknownTitles.add(cellVal);
				}
			}
		}
		
		return hasUnknown;
	}
	
	/**
	 * 判断是否包含所有配置的标题列
	 * @return
	 */
	private boolean missingTitle(){
		List<String> titleInFile = this.getTitle();
		boolean missing = false;
		for(String configTitle : this.titleList){
			if(titleInFile.contains(configTitle)){
				continue;
			}else{
				missing = true;
				this.missingTitles.add(configTitle);
			}
		}
		return missing;
	}
	
	/**
	 * 获取配置文件中的所有标题
	 * @return
	 */
	private List<String> getTitle(){
		List<String> titles = new ArrayList<String>();
		int lastIndex = this.row.getLastCellNum();
		for(int i = 0; i < lastIndex; i++){
			Cell cell = this.row.getCell(i);
			if(this.isEmptyCell(cell)){
				continue;
			}else{
				String cellVal = cell.getStringCellValue();
				titles.add(cellVal);
			}
		}
		return titles;
	}
	
	/**
	 * 判断单元格是否为空
	 * @param cell
	 * @return
	 */
	private boolean isEmptyCell(Cell cell){
		return cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK;
	}
}

