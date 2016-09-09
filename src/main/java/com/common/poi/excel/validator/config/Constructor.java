package com.common.poi.excel.validator.config;

import java.util.ArrayList;
import java.util.List;

import com.common.poi.excel.constant.ConfigConstants;
import com.common.poi.excel.validator.config.model.AttrConfig;
import com.common.poi.excel.validator.config.model.FileConfig;
import com.common.poi.excel.validator.config.model.TagConfig;

/**
 * @comment 校验配置文件数据结构的组织类
 */
public class Constructor {

	
	private Constructor(){}
	
	public static FileConfig getRootConfig(){
		
		AttrConfig runningModel = new AttrConfig(ConfigConstants.RUNNING_MODEL, ConfigConstants.AT_WILL, ConfigConstants.RUNNING_MODEL_RANGE);
		AttrConfig dir = new AttrConfig(ConfigConstants.STORAGE, ConfigConstants.AT_WILL, null);
		List<AttrConfig> settingAttrs = new ArrayList<AttrConfig>();
		settingAttrs.add(runningModel);
		settingAttrs.add(dir);
		TagConfig settingTag = new TagConfig(ConfigConstants.SETTINGS_NODE, ConfigConstants.AT_WILL,  null, settingAttrs);
		
		AttrConfig fileLocation = new AttrConfig(ConfigConstants.FILE_LOCATOIN, ConfigConstants.AT_WILL, null);
		List<AttrConfig> fileTagAttrs = new ArrayList<AttrConfig>();
		fileTagAttrs.add(fileLocation);
		TagConfig fileTag = new TagConfig(ConfigConstants.FILE_NODE, ConfigConstants.AT_LEAST_ONE, null, fileTagAttrs);
		List<TagConfig> fileList = new ArrayList<TagConfig>();
		fileList.add(fileTag);
		TagConfig filesTag = new TagConfig(ConfigConstants.FILES_NODE, ConfigConstants.ONLY_ONE, fileList, null);
		
		List<TagConfig> fileTags = new ArrayList<TagConfig>();
		fileTags.add(settingTag);
		fileTags.add(filesTag);
		
		TagConfig rootTag = new TagConfig(ConfigConstants.ROOT_NODE,ConfigConstants.ONLY_ONE, fileTags, null);
		
		FileConfig root = new FileConfig(rootTag);
		return root;
	}
	
	
	public static FileConfig getSingleConfig(){
		
		List<AttrConfig> cellAttrs = new ArrayList<AttrConfig>();
		AttrConfig prop = new AttrConfig(ConfigConstants.CELL_PROPERTY, ConfigConstants.ONLY_ONE, null);
		AttrConfig type = new AttrConfig(ConfigConstants.CELL_TYPE,ConfigConstants.ONLY_ONE, ConfigConstants.CELL_TYPE_RANGE);
		AttrConfig title = new AttrConfig(ConfigConstants.CELL_TITLE,ConfigConstants.ONLY_ONE, null);
		AttrConfig nullAble = new AttrConfig(ConfigConstants.CELL_NULL_ABLE,ConfigConstants.ONLY_ONE,ConfigConstants.CELL_NULL_ABLE_RANGE);
		cellAttrs.add(prop);
		cellAttrs.add(type);
		cellAttrs.add(title);
		cellAttrs.add(nullAble);
		TagConfig cell = new TagConfig(ConfigConstants.CELL_NODE, ConfigConstants.AT_LEAST_ONE, null, cellAttrs);
		List<TagConfig> cells = new ArrayList<TagConfig>();
		cells.add(cell);
		TagConfig row = new TagConfig(ConfigConstants.ROW_NODE, ConfigConstants.ONLY_ONE, cells, null);
		List<TagConfig> rows = new ArrayList<TagConfig>();
		rows.add(row);
		
		
		
		List<AttrConfig> fileAttrs = new ArrayList<AttrConfig>();
		AttrConfig id = new AttrConfig(ConfigConstants.FILE_ID, ConfigConstants.ONLY_ONE, null);
		AttrConfig clazz = new AttrConfig(ConfigConstants.FILE_CLASS, ConfigConstants.ONLY_ONE, null);
		fileAttrs.add(id);
		fileAttrs.add(clazz);
		TagConfig rootCfg = new TagConfig(ConfigConstants.FILE_NODE, ConfigConstants.ONLY_ONE, rows, fileAttrs);
		
		FileConfig cfg = new FileConfig(rootCfg);
		return cfg;
	}
}

