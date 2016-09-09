package com.common.poi.excel.config.stream;

import java.io.InputStream;

public interface StreamCreator {

	public InputStream getStream() throws Exception;
}

