package com.common.poi.excel.validator.title;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.validator.title.excel.Excel2003TitleValidator;
import com.common.poi.excel.validator.title.excel.Excel2007TitleValidator;
import com.common.poi.excel.constant.FileType;
import com.common.poi.excel.exception.R2D4Exception;
import com.common.poi.excel.model.FileModel;

public class TitleValidatorFactory {


    public static TitleValidator getInstance(File file, FileModel model, FileType fileType) {
        TitleValidator validator = null;
        //获取FileModel内的表头
        List<String> titleList = getTitleList(model);
        if (fileType.equals(FileType.EXCEL2003)) {
            validator = new Excel2003TitleValidator(file, titleList, model);
        } else if (fileType.equals(FileType.EXCEL2007)) {
            validator = new Excel2007TitleValidator(file, titleList, model);
        } else {
            throw new R2D4Exception("不支持的文件类型");
        }
        return validator;
    }


    private static List<String> getTitleList(FileModel model) {
        List<String> titleList = new ArrayList<String>();
        List<CellModel> cellModels = model.getRow().getCells();
        for (int i = 0, len = cellModels.size(); i < len; i++) {
            titleList.add(cellModels.get(i).getTitle());
        }
        return titleList;
    }
}

