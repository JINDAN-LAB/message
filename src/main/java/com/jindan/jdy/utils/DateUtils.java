package com.jindan.jdy.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 13348 on 2021/5/11.
 */
@Slf4j
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * Date format: "YYYYMMDDHHMMSS"
     */

    public static final String dateStr01 = "yyyyMMdd";
    public static final String dateStr02 = "yyyy-MM-dd";
    public static final String dateStr03 = "yyyy/MM/dd";
    public static final String dateStr04 = "yyyy/MM/dd HH:mm:ss";

    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String YYYYMM = "yyyy-MM";

    public DateUtils() {
    }


    public static void main(String[] args) throws ParseException {
//        String formatDate = getFormatDate("44301");
//        log.info("formatDate的值为："+formatDate);
        List<String> page1  = new ArrayList<>();
        String param = "20210528";
        page1.add("20210525");
        page1.add("20210526");
        page1.add("20210527");
        if(!page1.contains(param)){
            page1.add(param);
        }
        System.out.println(JSONObject.toJSONString(page1));
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
        if(date.length() == 5){// 44301
            Calendar calendar = new GregorianCalendar(1900,0,-1);
            Date gregorianDate = calendar.getTime();
            result = DateUtils.format(DateUtils.addDay(gregorianDate, Integer.parseInt(date)), DateUtils.YYYYMMDD);
        } else if(date.contains("/") && (date.length() >= 8 && date.length() <= 10)){// yyyy/MM/dd  yyyy/M/d
            SimpleDateFormat formatter = new SimpleDateFormat(dateStr03);
            Date parse = formatter.parse(date);
            result = formatterNew.format(parse);
        }
        else if(date.contains("-")){// yyyy-MM-dd
            return result;
        }else if(date.length() == 8){// yyyyMMdd
            SimpleDateFormat formatter = new SimpleDateFormat(dateStr01);
            Date parse = formatter.parse(date);
            result = formatterNew.format(parse);
        }else if(date.length() > 10){// yyyy/MM/dd HH:mm:ss
            SimpleDateFormat formatter = new SimpleDateFormat(dateStr04);
            Date parse = formatter.parse(date);
            result = formatterNew.format(parse);
        }
        return result;
    }

    public static String getCurrentDate(String var1) {
        return (new SimpleDateFormat(var1)).format(new Date());
    }

    public static Date parse(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(date);
            } catch (ParseException var2) {
                return null;
            }
        }
    }

    public static Date parse(String date, String format) {
        if (StringUtils.isEmpty(date)) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat(format)).parse(date);
            } catch (ParseException var3) {
                return null;
            }
        }
    }

    public static Date parseToYMD(String date) {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd")).parse(date);
        } catch (ParseException var2) {
            return null;
        }
    }

    public static String format(Date date) {
        return date != null ? (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date) : "/";
    }

    public static String format(Date date, String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

    public static Date addYear(Date date, int year) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(1, year);
        date = calendar.getTime();
        return date;
    }

    public static Date addMonth(Date date, int month) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(2, month);
        date = calendar.getTime();
        return date;
    }

    public static Date addDay(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, day);
        date = calendar.getTime();
        return date;
    }

    public static Date addHour(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(10, day);
        date = calendar.getTime();
        return date;
    }

    public static Date addMinute(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(12, day);
        date = calendar.getTime();
        return date;
    }

    public static Date addWeek(Date date, int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(5, day);
        date = calendar.getTime();
        return date;
    }

    public static Long getTimestamp() {
        return System.currentTimeMillis();
    }

    public static Map<String, String> getCurrentWeek(boolean addEndDay) {
        Map<String, String> map = new HashMap(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(2);
        int dayWeek = cal.get(7);
        if (dayWeek == 1) {
            dayWeek = 8;
        }

        cal.add(5, cal.getFirstDayOfWeek() - dayWeek);
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        map.put("beginWeek", weekBegin);
        cal.add(5, 4 + cal.getFirstDayOfWeek());
        if (addEndDay) {
            cal.add(5, 1);
        }

        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(addDay(sundayDate, -1)) + " 23:59:59";
        map.put("endWeek", weekEnd);
        return map;
    }

    public static Map<String, String> getCurrentMonth(boolean addEndDay) {
        Map<String, String> map = new HashMap(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("beginMonth", format(new Date(), "yyyy-MM") + "-01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(5, calendar.getActualMaximum(5));
        if (addEndDay) {
            calendar.add(5, 1);
        }

        map.put("endMonth", sdf.format(calendar.getTime()));
        return map;
    }

    public static Map<String, String> getAppointMonth(Date date, boolean addEndDay) {
        Map<String, String> map = new HashMap(2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("beginMonth", format(date, "yyyy-MM") + "-01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMaximum(5));
        if (addEndDay) {
            calendar.add(5, 1);
        }

        map.put("endMonth", sdf.format(calendar.getTime()));
        return map;
    }

    public static String secondToTime(long second) {
        long days = second / 86400L;
        second %= 86400L;
        long hours = second / 3600L;
        second %= 3600L;
        long minutes = second / 60L;
        second %= 60L;
        return 0L < days ? days + "天" + hours + "小时" + minutes + "分" + second + "秒" : hours + "小时" + minutes + "分" + second + "秒";
    }

    public static int getYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        return Integer.valueOf(sdf.format(date));
    }

    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, year);
        calendar.roll(6, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }
}
