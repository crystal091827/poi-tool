package com.common.poi.excel.util;

import java.io.File;

import com.common.poi.excel.config.ConfigReader;

/**
 * @comment 
 * 内部的文件创建器，它创建的文件，全都会保存在配置路径下
 * 如果没有配置路径，它会把文件保存在默认配置的路径下
 */
public class FileCreator {

	private static final String SLASH = "\\";
	private static final String path = "D:\\java-output";
	
	public static File create(String fileName){
		File dir = new File(path);
		if(dir.exists()){
			if(!dir.isDirectory()){
				dir.mkdirs();
			}
		}else{
			dir.mkdirs();
		}
		
		File file = new File(path + SLASH + fileName);
		return file;
	}
}

