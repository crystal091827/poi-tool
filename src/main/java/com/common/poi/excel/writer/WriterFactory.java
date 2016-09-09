package com.common.poi.excel.writer;

import java.util.List;

import com.common.poi.excel.constant.FileType;
import com.common.poi.excel.exception.R2D4Exception;
import com.common.poi.excel.model.FileModel;
import com.common.poi.excel.writer.excel.Excel2003Writer;
import com.common.poi.excel.writer.excel.Excel2007Writer;

public class WriterFactory {

	public static AbstractWriter getInstance(String fileName, FileModel model, List<?> data, FileType type){
		AbstractWriter writer = null;
		if(FileType.EXCEL2003.equals(type)){
			writer = new Excel2003Writer(data, fileName, model);
		}else if(FileType.EXCEL2007.equals(type)){
			writer = new Excel2007Writer(data, fileName, model);
		}else{
			throw new R2D4Exception("不支持的文件按类型");
		}
		
		return writer;
	}
}

