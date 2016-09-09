package com.common.poi.excel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Luopc on 2016-9-9-0009.
 */
public class ValidatorUtil {

    //带小数的字符串校验
    public static final String PATTERN_DECIMAL = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
    //整数校验
    public static final String PATTERN_INTEGER = "^(\\-|\\+)?\\d+(\\.0+)?$";

    //日期校验
    public static final String FORMAT_DATE_1 = "yyyy-MM-dd";
    public static final String FORMAT_DATE_2 = "yyyy/MM/dd";
    public static final String FORMAT_DATE_3 = "yyyy.MM.dd";
    public static final String FORMAT_DATE_4 = "yyyy年MM月dd";


    public static final String FORMAT_TIME_1 = "HH:mm";//只包含时分
    public static final String FORMAT_TIME_2 = "HH:mm:ss";//二十四小时
    public static final String FORMAT_TIME_3 = "HH:mm:ss a";//十二小时
    public static final String FORMAT_TIME_4 = "HH时mm分ss秒";//中文格式

    public static final String FORMAT_DATE_TIME_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_TIME_2 = "yyyy/MM/dd HH:mm:ss";
    public static final String FORMAT_DATE_TIME_3 = "yyyy.MM.dd HH:mm:ss";
    public static final String FORMAT_DATE_TIME_4 = "yyyy年MM月dd日 HH时mm分ss秒";

    /**
     * 使用正则表达式校验
     * @param rule
     * @param str
     * @return
     */
    public static boolean isValid(String rule ,String str) {
        Pattern pattern = Pattern.compile(rule,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 通过日期格式校验
     * @param dataFormat
     * @param str
     * @return
     */
    public static boolean isValidDate(String dataFormat , String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        try {// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }


    public static boolean isValidDateTime(String str){
        return isValidDate(FORMAT_DATE_TIME_1,str) || isValidDate(FORMAT_DATE_TIME_2,str)
                || isValidDate(FORMAT_DATE_TIME_3,str) || isValidDate(FORMAT_DATE_TIME_4,str);
    }

    public static boolean isValidDate(String str){
        return isValidDate(FORMAT_DATE_1,str) || isValidDate(FORMAT_DATE_2,str)
                || isValidDate(FORMAT_DATE_3,str) || isValidDate(FORMAT_DATE_4,str);
    }

    public static boolean isValidTime(String str){
        return isValidDate(FORMAT_TIME_1,str) || isValidDate(FORMAT_TIME_2,str)
                || isValidDate(FORMAT_TIME_3,str) || isValidDate(FORMAT_TIME_4,str);
    }


}
