package com.common.poi.excel.constant;

import com.common.poi.excel.model.CellValueModel;
import com.common.poi.excel.util.CellDateUtil;
import com.common.poi.excel.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Luopc on 2016-9-9-0009.
 */
public class CellValueServer {


    /**
     * 获取数字类型
     *
     * @return
     */
    public static BigDecimal getNumbericData(CellValueModel val) {
        int cellType = val.getCellType();//EXCEL表格内数据的类型
        Object cellVal = val.getVal();//EXCEL表格内数据值

        BigDecimal back = null;
        if (cellType == Cell.CELL_TYPE_NUMERIC) {
            double cellValue = val.getNumericCellValue();
            String va = new DecimalFormat("#").format(cellValue);
            back = new BigDecimal(va);
        } else if (cellType == Cell.CELL_TYPE_STRING) {
            String va = String.valueOf(cellVal);
            if (StringUtils.isBlank(va)) back = new BigDecimal(0);
            if ("/".equals(va)) back = new BigDecimal(0);
            else if (ValidatorUtil.isValid(ValidatorUtil.PATTERN_DECIMAL, va)) back = new BigDecimal(va);
        } else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
            if (val.getBooleanCellValue()) back = new BigDecimal(1);
            else back = new BigDecimal(0);
        }
        return back;
    }

    /**
     * 获取字符串类型
     *
     * @return
     */
    public static String getStringData(CellValueModel val) {
        int cellType = val.getCellType();//EXCEL表格内数据的类型
        Object cellVal = val.getVal();//EXCEL表格内数据值
        String back = "";
        if (cellType == Cell.CELL_TYPE_NUMERIC) {
            double cellValue = val.getNumericCellValue();
            String va = new DecimalFormat("#").format(cellValue);
            back = new BigDecimal(va).toString();
        } else if (cellType == Cell.CELL_TYPE_STRING) {
            back = val.getStringCellValue();
        } else if (cellType == Cell.CELL_TYPE_BOOLEAN) {
            if (val.getBooleanCellValue()) back = "是";
            else back = "否";
        }
        return back;
    }

    /**
     * 获取int类型数据
     *
     * @return
     */
    public static Integer getIntegerData(CellValueModel val) {
        return getNumbericData(val).intValue();
    }

    /**
     * 获取Long类型数据
     *
     * @return
     */
    public static Long getLongData(CellValueModel val) {
        return getNumbericData(val).longValue();
    }

    /**
     * 获取Float类型数据
     *
     * @return
     */
    public static Float getFloatData(CellValueModel val) {
        return getNumbericData(val).floatValue();
    }

    /**
     * 获取Double类型数据
     *
     * @return
     */
    public static Double getDoubledata(CellValueModel val) {
        return getNumbericData(val).doubleValue();
    }

    /**
     * 获取日期类型数据
     *
     * @return
     */
    public static Date getDateData(CellValueModel val) {
        Date date = null;
        int cellType = val.getCellType();//EXCEL表格内数据的类型
        Object cellVal = val.getVal();//EXCEL表格内数据值

        if (HSSFDateUtil.isCellDateFormatted(val.getCell())) {
            //如果是date类型则 ，获取该cell的date值
            date = HSSFDateUtil.getJavaDate(val.getCell().getNumericCellValue());
        } else if (cellType == Cell.CELL_TYPE_NUMERIC) { // 纯数字
            String va = parseDateExcel(val.getCell());
            date = CellDateUtil.cellValueToDate(va);
        } else if (cellType == Cell.CELL_TYPE_STRING) {
            String va = String.valueOf(cellVal);
            date = CellDateUtil.cellValueToDate(va);
        }
        return date;
    }

    private static String parseDateExcel(Cell cell) {
        String result = "";
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
            default:
                result = "";
                break;
        }
        return result;
    }
}
