package com.blog.framework.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期处理工具
 *
 * @author liuzw
 */
@Slf4j
public class DateUtil {

    private DateUtil() {
    }

    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE = "yyyy-MM-dd";

    public static final String DATE_D = "yyyy/MM/dd";

    public static final String DATE_TIME_PATTERN = "yyyyMMddHHmmss";

    /**
     * 时间转字符串--默认格式
     */
    public static String convertDateToString(Date date) {
        return getDateTime(DATE_TIME, date);
    }

    /**
     * 时间转字符串--按指定格式
     */
    public static String convertDateToString(String pattern, Date date) {
        return getDateTime(pattern, date);
    }

    /**
     * 格式化时间字符串
     */
    public static String convertStringDate(String pattern, String date) {
        return getDateTime(pattern, convertStringToDate(date));
    }

    private static String getDateTime(String pattern, Date date) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return returnValue;
    }

    /**
     * 字符串转时间--按默认格式
     * "yyyy-MM-dd HH:mm:ss"
     */
    public static Date convertStringToDate(final String strDate) {
        return convertStringToDate(DATE_TIME, strDate);
    }

    /**
     * 字符串转时间--按指定格式
     */
    public static Date convertStringToDate(String pattern, String strDate) {
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            log.error("ParseException: " + pe);
        }
        return date;
    }


    /**
     * 获取当前日期的前n天
     */
    public static Date getBeforeDay(Integer n) {
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.DATE, -1 * n);
        return cd.getTime();
    }

    /**
     * 获取当前日期的n个月前日期
     */
    public static Date getBeforeMonth(int n) {
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.MONTH, -n);
        return cd.getTime();
    }

    /**
     * 获取两个日期之间所有的日期列表（格式yyyy-MM-dd)
     */
    public static List<String> getBetweenDates(String date1, String date2) {
        if (StringUtils.isBlank(date1) || StringUtils.isBlank(date2)) {
            return Collections.emptyList();
        }
        List<String> list = new ArrayList<>();
        if (!date1.equals(date2)) {
            String tmp;
            // 确保 date1的日期不晚于date2
            if (date1.compareTo(date2) > 0) {
                tmp = date1;
                date1 = date2;
                date2 = tmp;
            }
            // 格式化
            tmp = convertDateToString(DATE, convertStringToDate(DATE, date1));
            while (tmp.compareTo(date2) <= 0) {
                list.add(tmp);
                tmp = addDate(DATE, tmp, "D", 1);
            }
        } else {
            list.add(date1);
        }
        return list;
    }


    /**
     * 年
     */
    private static final String Y = "Y";
    /**
     * 月
     */
    private static final String M = "M";
    /**
     * 日
     */
    private static final String D = "D";
    /**
     * 时
     */
    private static final String HH = "HH";
    /**
     * 分
     */
    private static final String MM = "MM";
    /**
     * 秒
     */
    private static final String SS = "SS";

    /**
     * 日期增加或者减少秒，分钟，天，月，年
     *
     * @param srcDate 时间
     * @param type    类型 Y M D HH MM SS 年月日时分秒
     * @param offset  （整数）
     * @return 增加或者减少之后的日期
     */
    public static Date addDate(Date srcDate, String type, int offset) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(srcDate);
        if (SS.equals(type)) {
            gc.add(GregorianCalendar.SECOND, offset);
        } else if (MM.equals(type)) {
            gc.add(GregorianCalendar.MINUTE, offset);
        } else if (HH.equals(type)) {
            gc.add(GregorianCalendar.HOUR, offset);
        } else if (D.equals(type)) {
            gc.add(GregorianCalendar.DATE, offset);
        } else if (M.equals(type)) {
            gc.add(GregorianCalendar.MONTH, offset);
        } else if (Y.equals(type)) {
            gc.add(GregorianCalendar.YEAR, offset);
        }
        return gc.getTime();
    }

    public static String addDate(String srcDate, String type, int offset) {
        return convertDateToString(addDate(convertStringToDate(srcDate), type, offset));
    }

    public static String addDate(String pattern, String srcDate, String type, int offset) {
        return convertDateToString(pattern, addDate(convertStringToDate(pattern, srcDate), type, offset));
    }

    /**
     * 将当前时间转换为当日零点零分零秒
     */
    public static Date getMorningDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 将日期改为当天的23点59分59秒
     */
    public static Date getEveningDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }


    /**
     * 获取当前年
     */
    public static String getNowYear() {
        Calendar cal = Calendar.getInstance();
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    /**
     * 获取上个月第一天
     */
    public static Date getBrforeMonthDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        //设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取上个月最后一天
     */
    public static Date getLastMonthDay() {
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        //设置为1号,当前日期既为本月第一天
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }

}


