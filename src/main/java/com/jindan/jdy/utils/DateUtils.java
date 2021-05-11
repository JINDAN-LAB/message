package com.jindan.jdy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 13348 on 2021/5/11.
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * Date format: "YYYYMMDDHHMMSS"
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String dateStr01 = "yyyyMMdd";
    public static final String dateStr02 = "yyyy-MM-dd";
    public static final String dateStr03 = "yyyy/MM/dd";
    public static final String dateStr04 = "yyyy/MM/dd HH:mm:ss";

    public static final String YYYYMM = "yyyyMM";

    public static void main(String[] args) throws ParseException {
        String formatDate = getFormatDate("20210419");
        System.out.println(formatDate);
    }
    /**
     *  转换时间格式，统一转换为  yyyy-MM-dd
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getFormatDate(String date) throws ParseException {
        if (date == null || "".equals(date)) {
            return null;
        }
        SimpleDateFormat formatterNew = new SimpleDateFormat(dateStr02);
        String result = date;
        if(date.length() == 8){// yyyyMMdd
            SimpleDateFormat formatter = new SimpleDateFormat(dateStr01);
            Date parse = formatter.parse(date);
            result = formatterNew.format(parse);
        }else if(date.contains("-")){// yyyy-MM-dd
            return result;
        }else if(date.contains("/") && date.length() == 10){// yyyy/MM/dd
            SimpleDateFormat formatter = new SimpleDateFormat(dateStr03);
            Date parse = formatter.parse(date);
            result = formatterNew.format(parse);
        }else if(date.length() > 10){// yyyy/MM/dd HH:mm:ss
            SimpleDateFormat formatter = new SimpleDateFormat(dateStr04);
            Date parse = formatter.parse(date);
            result = formatterNew.format(parse);
        }
        return result;
    }
}
