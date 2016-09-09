package com.common.poi.excel;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.common.poi.excel.config.ConfigReader;
import com.common.poi.excel.constant.FileType;
import com.common.poi.excel.model.CellValueModel;
import com.common.poi.excel.model.FileModel;
import com.common.poi.excel.reader.AbstractReader;
import com.common.poi.excel.util.SeperateDataUtil;
import com.common.poi.excel.validator.data.DataValidator;
import com.common.poi.excel.validator.title.TitleValidator;
import com.common.poi.excel.validator.title.TitleValidatorFactory;
import com.common.poi.excel.reader.ReaderFactory;

public class FileReader{

	private static FileReader instance = new FileReader();
	
	private FileReader(){}
	
	public static FileReader getInstance(){
		return instance;
	}
	
	/**
	 * 根据配置文件，从一个数据文件中读取数据的工具类
	 * @param id 配置文件中file标签的id，根据这个值去获取要读取的模板配置
	 * @param file 文件
	 * @param type 文件类型。参见FileType枚举类型
	 * @return
	 */
	public Feedback read(String id, File file, FileType type){
		FileModel model = ConfigReader.getFileModel(id);
		//校验表头是否与模板相符
		TitleValidator titleValidator = TitleValidatorFactory.getInstance(file, model, type);
		Feedback feedback = null;
		if(titleValidator.isValid()){
			AbstractReader reader = ReaderFactory.getReader(type, file, model);
			List<Map<String, CellValueModel>> origData = reader.read();

			DataValidator validator = new DataValidator(origData, model);
			Map<String, List<Integer>> errorIndexMap = validator.getErrorIndexMap();
			Map<String, List<Map<String, String>>> errorTip = validator.getErrorTip();
			//从Excel内哪找
			SeperateDataUtil util = new SeperateDataUtil(errorIndexMap, origData, model);
			List<Map<String,Object>> correctData = util.getCorrectData();
			Map<String, List<Map<String, CellValueModel>>> errorData = util.getErrorData();
			//List<?> result = ReadMapConverter.convert(DataConverter.convert(correctData), model);
			feedback = new Feedback(!errorData.isEmpty(), errorData, correctData , errorTip);
		}else{
			feedback = new Feedback(true, titleValidator.getInvalidInfo());
		}
		return feedback;
	}
	
	
	
	
	
	
	
	
	
}

