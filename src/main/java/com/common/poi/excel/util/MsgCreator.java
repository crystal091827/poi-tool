package com.common.poi.excel.util;

/**
 * @comment 错误信息生成器
 */
public class MsgCreator {

	
	
	
	private static final String START = "{";
	
	private static final String END = "}";
	
	
	public static String getMsg(String template, String[] info){
		
		if(info == null || info.length == 0){
			return template;
		}
		for(int i = 0, len = info.length; i < len; i++){
			String oldChar = getWidcard(i);
			String newChar = info[i];
			template = template.replace(oldChar, newChar);
		}
		return template;
	}
	
	
	private static String getWidcard(int i){
		
		StringBuilder builder = new StringBuilder();
		builder.append(START);
		builder.append(i);
		builder.append(END);
		
		return builder.toString();
	}
}

