package com.common.poi.excel.config;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.common.poi.excel.exception.R2D4Exception;
import com.common.poi.excel.util.CollectionUtil;

public class ResourceSearcher {

	private static final String SPLITER = "/";
	private static final String STAR = "*";
	private static final String REG_START = "\\*";
	private static final char STAR_CHAR = '*';
	private static final String BASE = "";
	private static final String FUZZY = "\\w+";
	
	public static List<File> search(String config){
		String [] paths = config.split(SPLITER);
		URL url = ClassLoader.getSystemResource(BASE);
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			throw new R2D4Exception(e.getMessage());
		}
		List<File> files = find(file, paths, 0);
		return files;
	}
 	
	
	private static List<File> find(File file, String[] paths, int index){
		String path = paths[index];
		
		List<File> matches = new ArrayList<File>();
		List<File> subFiles  = null;
		
		boolean allStar = allStars(paths[index]);
		if(allStar){
			do{
				index = index + 1;
			}while(allStars(paths[index]));
			boolean isDir = !isFile(paths, index);
			subFiles =  findAllMatches(file, paths[index], isDir);
		}else{
			subFiles = find(file, path);
		}
		
		boolean end = allStar ? isFile(paths , index) : isFile(paths, index);
		if(end){
			filter(subFiles, false);
			matches.addAll(subFiles);
		}else{
			filter(subFiles, true);
			if(CollectionUtil.isNotEmpty(subFiles)){
				for(File subFile : subFiles){
					matches.addAll(find(subFile, paths, index + 1));
				}
			}
		}
		
		
		return matches;
		
	}
	
	/**
	 * 根据名称aim查找所有符合要求的路径或者文件
	 * 只有当一个层级路径全是 "*" 的时候会走这个方法
	 * @param dir 在这个目录下查找
	 * @param aim 匹配的字符串
	 * @param isDir 要找的是否是目录，如果是文件，就是false
	 * @return
	 */
	private static List<File> findAllMatches(File dir, String aim, boolean isDir){
		
		List<File> res = new ArrayList<File>();
		File[] files = dir.listFiles();
		
		for(int i = 0, len = files.length; i < len; i++){
			File file = files[i];
			if(isDir){
				if(file.isDirectory()){
					if((aim.contains(STAR)&& match(file.getName(), aim))
							|| (!aim.contains(STAR) && file.getName().equals(aim))){
						res.add(file);
					}else{
						List<File> subFiles = findAllMatches(file, aim, isDir);
						res.addAll(subFiles);
					}
				}
			}else{
				if(file.isDirectory()){
					List<File> subFiles = findAllMatches(file, aim, isDir);
					res.addAll(subFiles);
				}else{
					if(match(file.getName(), aim)){
						res.add(file);
					}
				}
			}
		}
		
		return res;
	}
	
	/**
	 * 过滤文件集合。根据isDir是否为true过滤
	 * 当要得到的是目录时，isDir为true
	 * 当要得到的是文件时，isDir为false
	 * @param files
	 * @param isDir
	 */
	private static void filter(List<File> files , boolean isDir){
		
		for(int i = files.size() - 1 ; i >= 0; i--){
			File file = files.get(i);
			if(isDir){
				if(!file.isDirectory()){
					files.remove(i);
				}
			}else{
				if(file.isDirectory()){
					files.remove(i);
				}
			}
		}
	}
	
	
	private static boolean isFile(String[] paths, int index){
		return index == paths.length -1;
	}
	
	/**
	 * 判断一个文本字串是否全都是"*" 
	 * @param path
	 * @return
	 */
	private static boolean allStars(String path){
		boolean all = true;
		for(int i = 0, len = path.length(); i < len; i++){
			char elm = path.charAt(i);
			if(STAR_CHAR != elm){
				all = false;
				break;
			}
		}
		return all;
	}
	
	
	
	/**
	 * 在当前路径下找到和path匹配的子路径或者文件
	 * @param file 要寻找的路径
	 * @param name 要匹配的名称 
	 * @return
	 */
	private static List<File> find(File file, String name){
		List<File> matches = null;
		if(name.contains(STAR)){
			matches = fuzzy(file, name);
		}else{
			matches = new ArrayList<File>();
			File certain = certain(file, name);
			if(certain != null){
				matches.add(certain);
			}
		}
		return matches;
	}
	
	/**
	 * 根据name属性，匹配parent路径下的所有符合要求的直接子节点(文件或者目录)
	 * @param parent
	 * @param name
	 * @return
	 */
	private static List<File> fuzzy(File parent, String name){
		List<File> res = new ArrayList<File>();
		File[] files = parent.listFiles();
		if(CollectionUtil.isNotEmpty(files)){
			for(File file : files){
				String fileName = file.getName();
				if(match(fileName, name)){
					res.add(file);
				}
			}
		}
		return res;
	}
	
	/**
	 * 根据名称找到确定的目标(目录或者文件)
	 * @param parent 父层级目录
	 * @param name 名称
	 * @return
	 */
	private static File certain(File parent, String name){
		File res = null;
		File[] files = parent.listFiles();
		if(CollectionUtil.isNotEmpty(files)){
			for(File file : files){
				String fileName = file.getName();
				if(name.equals(fileName)){
					res = file;
					break;
				}
			}
		}
		return res;
	}
	
	/**
	 * 判断一个名称，是否和要搜寻的目标匹配
	 * @param name 名称
	 * @param aim 目标
	 * @return
	 */
	private static boolean match(String name, String aim){
		
		String[] arr = aim.split(REG_START);
		StringBuilder builder = new StringBuilder();
		for(int i = 0, len = arr.length; i < len; i++){
			if(i != 0){
				builder.append(FUZZY);
			}
			builder.append(arr[i]);
		}
		
		return name.matches(builder.toString());
	}
}

