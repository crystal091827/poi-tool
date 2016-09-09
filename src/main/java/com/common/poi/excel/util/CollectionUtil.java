package com.common.poi.excel.util;

import java.util.Collection;

public class CollectionUtil {

	
	public static boolean isEmpty(Collection<?> col){
		return col == null ? true : col.size() == 0;
	}
	
	public static boolean isNotEmpty(Collection<?> col){
		return !isEmpty(col);
	}
	
	public static boolean isEmpty(Object[] arr){
		return arr == null ? true : arr.length == 0;
	}
	
	public static boolean isNotEmpty(Object[] arr){
		return !isEmpty(arr);
	}
	
}

