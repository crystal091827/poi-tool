package com.common.poi.excel.constant;

public class ConfigConstants {
	
	/**
	 * 全局配置文件的根节点
	 */
	public static final String ROOT_NODE = "root";

	/**
	 * 全局配置节点标签
	 */
	public static final String SETTINGS_NODE = "settings";
	
	
	/**
	 * 运行模式
	 */
	public static final String RUNNING_MODEL = "model";
	
	/**
	 * 保存垃圾文件的DIR
	 */
	public static final String STORAGE = "storage";
	
	/**
	 * 默认的文件保存路径
	 */
	public static final String DEFUALT_STORAGE = "\\exportFile";
	
	/**
	 * 运行模式取值范围
	 */
	public static final String[] RUNNING_MODEL_RANGE = {ModelType.DEPLOY.getName(), ModelType.DEVELOP.getName()};
	
	
	/**
	 * 具体配置文件配置列表的根节点标签
	 */
	public static final String FILES_NODE = "files";
	
	/**
	 * 单个配置文件的标签
	 */
	public static final String FILE_NODE = "file";
	
	/**
	 * 单个配置文件的路径属性
	 */
	public static final String FILE_LOCATOIN = "location";
	
	/**
	 * 单个配置文件里的行配置
	 */
	public static final String ROW_NODE = "row";
	
	/**
	 * 单个配置文件里的格子配置
	 */
	public static final String CELL_NODE = "cell";
	
	/**
	 * 单个配置文件里的格子的property属性
	 */
	public static final String CELL_PROPERTY = "property";
	
	/**
	 * 单元格数据类型属性
	 */
	public static final String CELL_TYPE = "type";
	/**
	 * 单元格对应的标题
	 */
	public static final String CELL_TITLE = "title";
	
	/**
	 * 单元格是否可以为空
	 */
	public static final String CELL_NULL_ABLE = "nullAble";
	
	/**
	 * 单元格类型取值范围
	 */
	public static final String[] CELL_TYPE_RANGE = {CellType.DOUBLE.getMark(), CellType.FLOAT.getMark(), CellType.INTEGER.getMark(), CellType.LONG.getMark(), CellType.NUMBERIC.getMark(), CellType.STRING.getMark()};

	/**
	 * 单元格是否可空取值范围
	 */
	public static final String[] CELL_NULL_ABLE_RANGE = {"true", "false"};
	
	/**
	 * 单个文件配置中的ID属性
	 */
	public static final String FILE_ID = "id";
	/**
	 * 单个文件配置中的class属性
	 */
	public static final String FILE_CLASS = "class";
	
	/**
	 * 标签或者属性有且只有一个
	 */
	public static final String ONLY_ONE = "01";
	
	/**
	 * 标签或者属性可以有任意多个
	 */
	public static final String AT_WILL = "02";
	
	/**
	 * 标签或者属性至少要有一个
	 */
	public static final String AT_LEAST_ONE = "03";
	
	public static final String REAL_STORAGE_PATH = System.getProperty("user.dir");
}

