package com.common.poi.excel.config.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileStreamCreator implements StreamCreator {
	
	private File file;
	
	public FileStreamCreator(File file){
		this.file = file;
	}

	@Override
	public InputStream getStream() throws Exception{
		return new FileInputStream(file);
	}

}

