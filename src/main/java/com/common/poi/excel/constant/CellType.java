package com.common.poi.excel.constant;

import com.common.poi.excel.exception.R2D4Exception;

import java.util.Date;

public enum CellType {
	
	
	
	
	

	NUMBERIC(1, "数字型", "number", Number.class),
	STRING(2, "字符串型", "string", String.class),
	INTEGER(3, "整型", "int", int.class),
	LONG(4, "长整型" , "long", long.class),
	FLOAT(5, "单精度", "float", float.class),
	DOUBLE(6, "双精度", "double", double.class),
	PERCENTAGE(7,"百分比","double", double.class),
	DATE(8,"日期类型","double", Date.class),
	TIME(9,"时间类型","double", Date.class),
	DATE_TIME(10,"日期时间","double", Date.class);

	
	public static final int TYPE_NUMBERIC  = 1; 
	public static final int TYPE_STRING = 2;
	public static final int TYPE_INTEGER = 3;
	public static final int TYPE_LONG = 4;
	public static final int TYPE_FLOAT = 5;
	public static final int TYPE_DOUBLE = 6;
	public static final int TYPE_PERCENTAGE = 7;
	public static final int TYPE_DATE = 8;
	public static final int TYPE_TIME = 9;
	public static final int TYPE_DATE_TIME = 10;

	private static final String UNSUPPORT_CELL_TYPE = "不支持的数据类型";
	
	private int code;
	
	private String name;
	
	private String mark;
	
	private Class<?> clazz;

	private CellType(int code, String name, String mark, Class<?> clazz) {
		this.code = code;
		this.name = name;
		this.mark = mark;
		this.clazz = clazz;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getMark() {
		return mark;
	}
	
	public Class<?> getClazz(){
		return this.clazz;
	}
	
	
	public static CellType get(String mark){
		CellType res = null;
		if("varchar".equals(mark)){
			res = STRING;
		}else if("number".equals(mark)){
			res = NUMBERIC;
		}else if("string".equals(mark)){
			res = STRING;
		}else if("int".equals(mark)){
			res = INTEGER;
		}else if("long".equals(mark)){
			res = LONG;
		}else if("float".equals(mark)){
			res = FLOAT;
		}else if("double".equals(mark)){
			res = DOUBLE;
		}else if("clob".equals(mark)){
			res = STRING;
		}else if("date".equals(mark)){
			res = DATE;
		}else if("percentage".equals(mark)){
			res = PERCENTAGE;
		}else if("time".equals(mark)){
			res = TIME;
		}else if("dataTime".equals(mark)){
			res = DATE_TIME;
		}else{
			throw new R2D4Exception(UNSUPPORT_CELL_TYPE);
		}
		
		return res;
		
	}
}

