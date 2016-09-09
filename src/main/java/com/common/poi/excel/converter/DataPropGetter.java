package com.common.poi.excel.converter;

import java.lang.reflect.Method;

import com.common.poi.excel.exception.R2D4Exception;
import com.common.poi.excel.util.StringUtil;

public class DataPropGetter {
	
	private static final String POINT = ".";
	private static final String GET_METHOD_HEAD = "get";

	public static Object getPropVal(String fullPropName, Object data){
		
		Class<?> clazz = data.getClass();
		String propName = null;
		String restName = null;
		if(fullPropName.contains(POINT)){
			propName = fullPropName.substring(0,  fullPropName.indexOf(POINT));
			restName = fullPropName.substring(fullPropName.indexOf(POINT) + 1);
		}else{
			propName = fullPropName;
		}
		
		Object res = null;
		String methodName = GET_METHOD_HEAD + propName.substring(0, 1).toUpperCase() + propName.substring(1);
		try {
			Method method = clazz.getMethod(methodName);
			res = method.invoke(data);
		} catch (Exception e) {
			e.printStackTrace();
			throw new R2D4Exception(e.getMessage());
		}
		
		if(StringUtil.isEmpty(restName) || res == null){
			return res;
		}else{
			return getPropVal(restName, res);
		}
	}
}

