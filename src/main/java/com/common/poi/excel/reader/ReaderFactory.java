package com.common.poi.excel.reader;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import com.common.poi.excel.constant.FileType;
import com.common.poi.excel.exception.R2D4Exception;
import com.common.poi.excel.model.FileModel;
import com.common.poi.excel.reader.excel.Excel2007Reader;
import com.common.poi.excel.reader.util.TitlePropUtil;
import com.common.poi.excel.reader.excel.Excel2003Reader;

public class ReaderFactory {

	public static AbstractReader getReader(String readerClazz, InputStream in, FileModel model){
		return null;
	}
	
	public static AbstractReader getReader(FileType type, File file, FileModel model){
		AbstractReader reader = null;
		//根据模板生成属性集合
		Map<String, String> propMap = TitlePropUtil.initPropMaps(model);
		if(type.equals(FileType.EXCEL2003)){
			reader = new Excel2003Reader(file, propMap, model);
		}else if(type.equals(FileType.EXCEL2007)){
			reader = new Excel2007Reader(file, propMap ,model);
		}else{
			throw new R2D4Exception("不支持的文件按类型");
		}
		return reader;
	}
}

