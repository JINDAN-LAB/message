package com.jindan.jdy.controller.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {


    public static String GetGUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static long getDistanceDays(String str1, String str2) throws Exception {
        if( str1 == null || str1.equals(" ") ){
            return -1;
        }
        if( str2 == null || str2.equals(" ") ){
            return 10;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff = time1 - time2;
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static long getDateStringDistanceDays( String str1,Date two) throws Exception {
        if( str1 == null || str1.equals(" ") ){
            return -1;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        long days = 0;
        try {
            one = df.parse(str1);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff = time1 - time2;
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static String getPresenttime() throws Exception {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strdater = sdf.format(d);
        return strdater;
    }

public static String getminus(String dates,String  day) throws Exception {
    Date d = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    Date date = null;
    Date newDate = null;
    try {
        date = dateFormat.parse(dates); // 指定日期
        newDate = addDate(date, Integer.valueOf(day)); // 指定日期天数
    } catch (ParseException e) {
        e.printStackTrace();
    }
    System.out.println(dateFormat.format(date));// 输出格式化后的日期
    System.out.println(dateFormat.format(newDate));
    return dateFormat.format(newDate);

}

    public static Date addDate(Date date, long day) throws ParseException {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time -= day; // 相减得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }


}
