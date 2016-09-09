package com.common.poi.excel.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Luopc on 2016-9-9-0009.
 */
public class CellDateUtil {
    /**
     * 字符串转换为java.util.Date<br>
     * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br>
     * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br>
     * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br>
     * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br>
     * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br>
     *
     * @param time String 字符串<br>
     * @return Date 日期<br>
     */
    public static Date stringToDate(String time) {
        SimpleDateFormat formatter;
        int tempPos = time.indexOf("AD");
        time = time.trim();
        formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        if (tempPos > -1) {
            time = time.substring(0, tempPos) + "公元" + time.substring(tempPos + "AD".length());//china
            formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss z");
        }
        tempPos = time.indexOf("-");
        if (tempPos > -1 && (time.indexOf(" ") < 0)) {
            formatter = new SimpleDateFormat("yyyyMMddHHmmssZ");
        } else if ((time.indexOf("/") > -1) && (time.indexOf(" ") > -1)) {
            formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        } else if ((time.indexOf("-") > -1) && (time.indexOf(" ") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if ((time.indexOf("年") > -1) && (time.indexOf(" ") > -1)) {
            formatter = new SimpleDateFormat("yyyy年MM月dd KK:mm:ss");
        }else if ((time.indexOf("/") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        } else if ((time.indexOf("-") > -1) && (time.indexOf("am") > -1) || (time.indexOf("pm") > -1)) {
            formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
        }
        ParsePosition pos = new ParsePosition(0);
        java.util.Date ctime = formatter.parse(time, pos);
        return ctime;
    }

    public static Date cellValueToDate(String time) {
        String str = time.trim();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_DATE_TIME_1,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_DATE_TIME_1);
        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_DATE_TIME_2,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_DATE_TIME_2);
        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_DATE_TIME_3,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_DATE_TIME_3);
        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_DATE_TIME_4,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_DATE_TIME_4);

        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_DATE_1,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_DATE_1);
        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_DATE_2,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_DATE_2);
        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_DATE_3,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_DATE_3);
        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_DATE_4,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_DATE_4);

        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_TIME_1,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_TIME_1);
        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_TIME_2,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_TIME_2);
        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_TIME_3,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_TIME_3);
        }else if(ValidatorUtil.isValidDate(ValidatorUtil.FORMAT_TIME_4,str)) {
            formatter = new SimpleDateFormat(ValidatorUtil.FORMAT_TIME_4);
        }
        ParsePosition pos = new ParsePosition(0);
        java.util.Date ctime = formatter.parse(str, pos);
        return ctime;
    }

}
