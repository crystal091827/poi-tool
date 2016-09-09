package com.common.poi.excel;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.common.poi.excel.config.ConfigReader;
import com.common.poi.excel.model.FileModel;
import com.common.poi.excel.writer.AbstractWriter;
import com.common.poi.excel.writer.WriterFactory;
import com.common.poi.excel.constant.FileType;

public class FileWriter{

	
	private static FileWriter instance = new FileWriter();
	
	
	private FileWriter(){};
	
	public static FileWriter getInstance(){
		return instance;
	}
	
	
	/**
	 * 把数据写到文件中的工具方法
	 * @param id 配置文件中file标签的id，根据这个值去获取要生成哪个导出文件
	 * @param fileName 用户希望的导出文件的名称
	 * @param data 数据
	 * @param type 文件类型。目前只支持了Excel2003和Excel2007,参数参看FileType枚举类型
	 * @return 返回一个文件。
	 */
	public File writeFile(String id, String fileName, List<?> data, FileType type){
		FileModel model = ConfigReader.getFileModel(id);
		AbstractWriter writer = WriterFactory.getInstance(fileName, model, data, type);
		return writer.write();
	}

	/**
	 * 把数据写到文件中的工具方法
	 * @param id 配置文件中file标签的id，根据这个值去获取要生成哪个导出文件
	 * @param fileName 用户希望的导出文件的名称
	 * @param data 数据
	 * @param type 文件类型。目前只支持了Excel2003和Excel2007,参数参看FileType枚举类型
	 * @return 返回一个文件。
	 */
	public File writeMapDataFile(String id, String fileName, List<Map<String,Object>> data, FileType type){
		FileModel model = ConfigReader.getFileModel(id);
		AbstractWriter writer = WriterFactory.getInstance(fileName, model, data, type);
		return writer.write();
	}
	
}

