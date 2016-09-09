package com.common.poi.excel.validator.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.common.poi.excel.constant.CellType;
import com.common.poi.excel.model.CellModel;
import com.common.poi.excel.model.CellValueModel;
import com.common.poi.excel.util.CellModelSearcher;
import com.common.poi.excel.model.FileModel;
import com.common.poi.excel.util.ValidatorUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @comment 数据类型校验器
 */
public class TypeValidator extends AbstractValidator {
    /**
     * @param model
     */
    public TypeValidator(FileModel model) {
        super(model);
        this.errorTypeName = "数据类型不符合要求";
        this.tipList = Lists.newArrayList();
    }

    @Override
    public boolean validate(Map<String, CellValueModel> rowData) {
        boolean ok = true;
        for (String propName : rowData.keySet()) {
            CellValueModel val = rowData.get(propName);
            CellModel cellModel = CellModelSearcher.getCellModelByPropName(propName, this.model);
            if (val.getVal() != null && !this.correctType(val, cellModel)) {
                Map<String,String> error = new HashMap<String,String>();
                error.put(propName,"第"+val.getCell().getRowIndex()+"列"+val.getCell().getColumnIndex()+"行:" + cellModel.getTip());
                tipList.add(error);
                ok = false;
                break;
            }
        }
        return ok;
    }




    private boolean correctType(CellValueModel val, CellModel cellModel) {

        int modelType = cellModel.getType().getCode();//模板要求的单元数据类型
        /**
         * 如果配置中是的数据类型和单元格值模型的数据类型是否一致
         * int CELL_TYPE_NUMERIC = 0; 数字类型
         * int CELL_TYPE_STRING = 1; 字符串
         * int CELL_TYPE_FORMULA = 2; 公式
         * int CELL_TYPE_BLANK = 3; 空白单元格
         * int CELL_TYPE_BOOLEAN = 4; 布尔类型
         * int CELL_TYPE_ERROR = 5; 错误的类型
         */
        int cellType = val.getCellType();//EXCEL表格内数据的类型
        Object cellVal = val.getVal();//EXCEL表格内数据值


        if (cellType == Cell.CELL_TYPE_ERROR) return false;

        if (cellType == Cell.CELL_TYPE_STRING) {//EXCEL内是字符串
            String va = String.valueOf(cellVal);
            if (modelType == CellType.STRING.getCode()) {
                return true;
            } else {//增加自己的校验
                if (StringUtils.isNotBlank(va)) {
                    if (modelType == CellType.TYPE_NUMBERIC) {
                        if ("/".equals(va)) return "/".equals(va)
                                || ValidatorUtil.isValid(ValidatorUtil.PATTERN_DECIMAL, va);

                    } else if (modelType == CellType.TYPE_DOUBLE) {
                        if ("/".equals(va)) return "/".equals(va)
                                || ValidatorUtil.isValid(ValidatorUtil.PATTERN_DECIMAL, va);

                    } else if (modelType == CellType.TYPE_FLOAT) {
                        if ("/".equals(va)) return "/".equals(va)
                                || ValidatorUtil.isValid(ValidatorUtil.PATTERN_DECIMAL, va);

                    } else if (modelType == CellType.TYPE_INTEGER) {
                        if ("/".equals(va)) return "/".equals(va)
                                || ValidatorUtil.isValid(ValidatorUtil.PATTERN_DECIMAL, va);

                    } else if (modelType == CellType.TYPE_LONG) {
                        if ("/".equals(va)) return "/".equals(va)
                                || ValidatorUtil.isValid(ValidatorUtil.PATTERN_DECIMAL, va);

                    } else if (modelType == CellType.TYPE_PERCENTAGE) {
                        if ("/".equals(va)) return "/".equals(va)
                                || ValidatorUtil.isValid(ValidatorUtil.PATTERN_DECIMAL, va);

                    } else if (modelType == CellType.TYPE_DATE) {
                        return ValidatorUtil.isValidDate(va);

                    } else if (modelType == CellType.TYPE_TIME) {
                        return ValidatorUtil.isValidTime(va);

                    } else if (modelType == CellType.TYPE_DATE_TIME) {
                        return ValidatorUtil.isValidDateTime(va);

                    } else {

                    }
                }

            }
        } else if (cellType == Cell.CELL_TYPE_NUMERIC) {//EXCEL内是数字

            if (modelType == CellType.TYPE_NUMBERIC || modelType == CellType.TYPE_DOUBLE || modelType == CellType.TYPE_FLOAT
                    || modelType == CellType.TYPE_INTEGER || modelType == CellType.TYPE_LONG || modelType == CellType.TYPE_PERCENTAGE) {
                return true;

            } else if (modelType == CellType.TYPE_DATE || modelType == CellType.TYPE_TIME || modelType == CellType.TYPE_DATE_TIME) {
                return true;

            } else if (modelType == CellType.TYPE_STRING) {
                return true;

            }
        } else if (cellType == Cell.CELL_TYPE_BOOLEAN) {//Excel内是布尔类型
            if (modelType == CellType.TYPE_STRING) {
                return true;
            } else if (modelType == CellType.TYPE_NUMBERIC) {
                return true;
            }
        }
        return false;
    }
}



