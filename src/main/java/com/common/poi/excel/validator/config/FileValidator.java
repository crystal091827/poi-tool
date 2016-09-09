package com.common.poi.excel.validator.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.common.poi.excel.constant.ConfigConstants;
import com.common.poi.excel.util.MsgCreator;
import org.dom4j.Attribute;
import org.dom4j.Element;

import com.common.poi.excel.exception.R2D4Exception;
import com.common.poi.excel.util.CollectionUtil;
import com.common.poi.excel.validator.config.model.AttrConfig;
import com.common.poi.excel.validator.config.model.FileConfig;
import com.common.poi.excel.validator.config.model.TagConfig;

/**
 * @comment 配置文件校验工具
 */
public class FileValidator {

	private FileValidator(){};
	
	
	private static final String FILE_NOT_EXIST = "配置文件: {0} 木有找到诶亲~~";
	
	/**
	 * 判断文件是否存在的方法
	 * @param path
	 */
	public static void fileExist(String path){
		InputStream in = null;
		try{	
			FileValidator.class.getClassLoader().getResourceAsStream(path);
			if(in == null || in.available() == 0){
				throw new R2D4Exception(MsgCreator.getMsg(FILE_NOT_EXIST, new String[]{path}));
			}
		}catch(Exception e ){
			throw new R2D4Exception(e.getMessage());
		}finally{
			if(in != null){
				try {in.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
	}
	
	
	/**
	 * 校验配置文件的对外接口。
	 * @param root 配置文件的根节点
	 * @param config 配置文件的数据结构
	 */
	public static void validate(Element root, FileConfig config){
		TagConfig rootConfig = config.getRootConfig();
		validateTag(root, rootConfig);
	}
	
	private static final String NOT_ALLOW_TAG_NAME = "不允许的配置标签：";
	private static final String CAN_NOT_HAS_ANY_ATTR = "标签{0}不允许设置任何属性";
	private static final String NOT_ALLOW_ATTR_NAME = "标签{0}中不允许出现的属性：{1}";
	private static final String CAN_NOT_HAS_SUB_TAG = "标签{0}不允许设置子标签";
	private static final String NOT_ALLOW_SUB_TAG_NAME = "标签{0}不允许出现子标签: {1}";
	private static final String NOT_ALLOW_ATTR_VALUE = "标签{0}的{1}属性出现不允许的值:{2}";
	/**
	 * 校验节点标签的方法
	 * @param node
	 * @param tagConfig
	 */
	@SuppressWarnings("unchecked")
	private static void validateTag(Element node, TagConfig tagConfig){
		
		/**
		 * 上来校验节点名字和允许的名字是否匹配
		 */
		String nodeName = node.getName();
		String allowName = tagConfig.getName();
		if(!nodeName.equals(allowName)){
			throw new R2D4Exception(NOT_ALLOW_TAG_NAME + nodeName);
		}
		
		/**
		 * 然后校验属性是否OK
		 * 如果标签允许的配置属性为空，那么需要检查标签是否有属性，有属性就报错。
		 * 如果标签包含有配置属性，那么需要检验配置的标签的属性是否合法
		 */
		List<AttrConfig> allowAttrs = tagConfig.getAttrs();
		List<Attribute> attrs = (List<Attribute>)node.attributes();
		
		if(CollectionUtil.isEmpty(allowAttrs)){
			if(!CollectionUtil.isEmpty(attrs)){
				throw new R2D4Exception(MsgCreator.getMsg(CAN_NOT_HAS_ANY_ATTR, new String[]{nodeName}));
			}
		}else{
			/**
			 * 首先执行一次循环，去找配置文件中的属性，是否全都是允许的属性
			 */
			for(Attribute attr : attrs){
				/**
				 * 需要去根据attr的名称，找到具体的允许的AttrConfig
				 */
				String attrName = attr.getName();
				AttrConfig attrConfig = getAttrConfigByName(allowAttrs, attrName);
				/**
				 * 如果AttrConfig没找到，说明配置的是不合法的属性
				 */
				if(attrConfig == null){
					throw new R2D4Exception(MsgCreator.getMsg(NOT_ALLOW_ATTR_NAME, new String[]{nodeName, attrName}));
				}
			}
			
			
			/**
			 * 然后去循环允许的属性
			 * 来检查配置文件中的属性是否合法
			 */
			for(AttrConfig allowAttr : allowAttrs){
				List<Attribute> sameNameAttr = getAtrributeByName(attrs, allowAttr.getName());
				String rs = validateAttrNum(sameNameAttr, allowAttr, nodeName);
				if(rs.equals(NUM_CORRECT)){
					if(CollectionUtil.isNotEmpty(sameNameAttr)){
						for(Attribute attr : sameNameAttr){
							validateAttr(nodeName, attr, allowAttr);
						}
					}
				}else{
					throw new R2D4Exception(rs);
				}
			} 
		}
		
		
		/**
		 * 最后校验子节点
		 */
		List<TagConfig> subTags = tagConfig.getSubTags();
		List<Element> subNodes = node.elements();
		if(CollectionUtil.isEmpty(subTags)){
			/**
			 * 如果允许的子标签是空，那么就去判断子标签是否为空
			 * 是就抛异常
			 */
			if(!CollectionUtil.isEmpty(subNodes)){
				throw new R2D4Exception(MsgCreator.getMsg(CAN_NOT_HAS_SUB_TAG, new String[]{nodeName}));
			}
		}else{
			/**
			 * 先去校验配置文件中的子节点是否都是允许配置的
			 */
			for(Element subNode : subNodes){
				String subNodeName = subNode.getName();
				TagConfig subTagConfig = getTagConfigByNamme(subTags, subNodeName);
				/**
				 * 根据名称去找允许配置的子节点，如果不存在，就抛出异常
				 */
				if(subTagConfig == null){
					throw new R2D4Exception(MsgCreator.getMsg(NOT_ALLOW_SUB_TAG_NAME, new String[]{nodeName,subNodeName}));
				}
			}
			
			/**
			 * 然后去循环允许子节点
			 * 来检查配置文件中的子节点是否合法
			 */
			for(TagConfig subTag : subTags){
				String subTagName = subTag.getName();
				List<Element> sameNameElms = getSubElementByName(subNodes, subTagName);
				String rs = validateElmNum(sameNameElms, subTag, nodeName);
				if(rs.equals(NUM_CORRECT)){
					if(CollectionUtil.isNotEmpty(sameNameElms)){
						for(Element subElm : sameNameElms){
							validateTag(subElm, subTag);
						}
					}
				}else{
					throw new R2D4Exception(rs);
				}
				
			}
		}
	}
	
	private static final String ATTR_ONLY_ONE_BUT_NONE = "标签{0}中应有且只有一个的{1}属性,目前缺少一个";
	private static final String ATTR_ONLY_ONE_BUT_TOO_MANY = "标签{0}中应有且只有一个{1}属性,目前有多个";
	private static final String ATTR_AT_LIEST_ONE_BUT_NONE = "标签{0}中应至少有一个{1}属性,目前没有";
	private static final String NUM_CORRECT = "NUM_CORRECT";
	
	
	
	/**
	 * 判断属性个数是否合法
	 * @param sameNameAttrs
	 * @param allowAttr
	 * @param tagName
	 * @return
	 */
	private static String validateAttrNum(List<Attribute> sameNameAttrs, AttrConfig allowAttr, String tagName){
		String res = NUM_CORRECT;
		String quantity = allowAttr.getQuantity();
		if(quantity.equals(ConfigConstants.ONLY_ONE)){
			if(CollectionUtil.isEmpty(sameNameAttrs)){
				res = MsgCreator.getMsg(ATTR_ONLY_ONE_BUT_NONE,new String[] {tagName, allowAttr.getName()});
			}else if(sameNameAttrs.size() > 1){
				res = MsgCreator.getMsg(ATTR_ONLY_ONE_BUT_TOO_MANY, new String[]{tagName, allowAttr.getName()});
			}
		}else if(quantity.equals(ConfigConstants.AT_LEAST_ONE)){
			if(CollectionUtil.isEmpty(sameNameAttrs)){
				res = MsgCreator.getMsg(ATTR_AT_LIEST_ONE_BUT_NONE, new String[]{tagName, allowAttr.getName()});
			}
		}
		return res;
	}
	
	
	private static final String ELM_ONLY_ONE_BUT_NONE = "标签{0}中应有且只有一个{1}子标签,目前缺少一个";
	private static final String ELM_ONLY_ONE_BUT_TOO_MANY = "标签{0}中应有且只有一个{1}子标签,目前有多个";
	private static final String ELM_AT_LEAST_ONE_BUT_NONE = "标签{0}中应至少有一个{1}属性,目前没有";
	
	/**
	 * 判断子标签个数是否合法
	 * @param elements
	 * @param tagConfig
	 * @param tagName
	 * @return
	 */
	private static String validateElmNum(List<Element> elements, TagConfig tagConfig, String tagName){
		String res = NUM_CORRECT;
		String quantity = tagConfig.getQuantity();
		if(quantity.equals(ConfigConstants.ONLY_ONE)){
			if(CollectionUtil.isEmpty(elements)){
				res = MsgCreator.getMsg(ELM_ONLY_ONE_BUT_NONE, new String[]{tagName, tagConfig.getName()});
			}else if(elements.size() > 1){
				res = MsgCreator.getMsg(ELM_ONLY_ONE_BUT_TOO_MANY, new String[]{tagName, tagConfig.getName()});
			}			
		}else if(quantity.equals(ConfigConstants.AT_LEAST_ONE)){
			if(CollectionUtil.isEmpty(elements)){
				res = MsgCreator.getMsg(ELM_AT_LEAST_ONE_BUT_NONE, new String[]{tagName, tagConfig.getName()});
			}
		}
		return res;
	}
	
	
	/**
	 * 校验节点属性的方法
	 * @param attr
	 * @param attrConfig
	 */
	private static void validateAttr(String nodeName, Attribute attr, AttrConfig attrConfig){
		String[] range = attrConfig.getRange();
		String value = attr.getValue().trim();
		if(CollectionUtil.isNotEmpty(range)){
			if(!hasValue(range, value)){
				throw new R2D4Exception(MsgCreator.getMsg(NOT_ALLOW_ATTR_VALUE, new String[]{nodeName, attr.getName(), value}));
			}
		}
	}
	
	private static boolean hasValue(String[] range, String value){
		boolean has = false;
		for(String rangeVal : range){
			if(rangeVal.equals(value)){
				has = true;
				break;
			}
		}
		return has;
	}
	
	
	private static TagConfig getTagConfigByNamme(List<TagConfig> subTags, String tagName){
		TagConfig cfg = null;
		if(CollectionUtil.isEmpty(subTags)){
			return cfg;
		}
		
		for(TagConfig config : subTags){
			if(config.getName().equals(tagName)){
				cfg = config;
				break;
			}
		}
		return cfg;
	}
	
	private static AttrConfig getAttrConfigByName(List<AttrConfig> attrConfigs, String attrName){
		AttrConfig cfg = null;
		if(CollectionUtil.isEmpty(attrConfigs)){
			return cfg;
		}
		
		for(AttrConfig config : attrConfigs){
			if(config.getName().equals(attrName)){
				cfg = config;
				break;
			}
		}
		return cfg;
	}
	
	/**
	 * 根据属性名称去获取配置中的属性
	 * @param attrs
	 * @param attrName
	 * @return
	 */
	private static List<Attribute> getAtrributeByName(List<Attribute> attrs, String attrName){
		
		List<Attribute> res = new ArrayList<Attribute>();
		for(Attribute attr : attrs){
			String name = attr.getName();
			if(attrName.equals(name)){
				res.add(attr);
			}
		}
		return res;
	}
	
	/**
	 * 根据节点名称从节点列表中夺取节点集合
	 * @param elements
	 * @param nodeName
	 * @return
	 */
	private static List<Element> getSubElementByName(List<Element> elements, String nodeName){
		List<Element> res = new ArrayList<Element>();
		for(Element elm : elements){
			if(elm.getName().equals(nodeName)){
				res.add(elm);
			}
		}
		return res;
		
	}
	
}

