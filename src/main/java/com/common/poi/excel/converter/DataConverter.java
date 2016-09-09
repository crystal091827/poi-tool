package com.common.poi.excel.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.poi.excel.util.CollectionUtil;

public class DataConverter {

	
	private static final String POINT = ".";
	
	public static List<Map<String, Object>> convert(List<Map<String, Object>> params){
		List<Map<String, Object>> reses = new ArrayList<Map<String, Object>>();
		
		if(CollectionUtil.isEmpty(params)){
			return reses;
		}
		
		for(Map<String, Object> param : params){
			Map<String , Object> res = convert(param);
			reses.add(res);
		}
		return reses;
	}
	
	public static Map<String, Object> convert(Map<String, Object> param){
		
		Map<String, Object> container = new HashMap<String, Object>();
		for(String propertyName : param.keySet()){
			split(propertyName, param.get(propertyName), container);
		}
		return container;
	}
	
	@SuppressWarnings("unchecked")
	private static void split(String propertyName, Object val, Map<String, Object> container){
		int index =  propertyName.indexOf(POINT);
		if(index == -1 || val == null){
			container.put(propertyName, val);
		}else{
			String currentLevelName = propertyName.substring(0, index);
			String nextLevelName = propertyName.substring(index + 1);
			if(!container.containsKey(currentLevelName)){
				Map<String, Object> subContainer = new HashMap<String, Object>();
				container.put(currentLevelName, subContainer);
			}
			Map<String, Object> subContainer = (Map<String, Object>)container.get(currentLevelName);
			split(nextLevelName, val, subContainer);
		}
	}
}

