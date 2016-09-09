package com.common.poi.excel.config.stream;

import java.io.InputStream;

public class ResourceStreamCreator implements StreamCreator {

	
	private String filePath;
	
	public ResourceStreamCreator(String filePath){
		this.filePath = filePath;
	}
	
	@Override
	public InputStream getStream() throws Exception {
		return ClassLoader.getSystemResourceAsStream(filePath);
	}

}

