package com.drore.cloud.control.manger.common.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月25日 15:01 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public class DateUtils {
    public static final String YMD = "yyyyMMdd";
    public static final String YMD_SLASH = "yyyy/MM/dd";
    public static final String YMD_DASH = "yyyy-MM-dd";
    public static final String YMD_DASH_WITH_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String YMD_DASH_WITH_TIME_SIMPLE = "yyyyMMddHHmmss";
    public static final String YDM_SLASH = "yyyy/dd/MM";
    public static final String YDM_DASH = "yyyy-MM-dd";
    public static final String YM_DASH = "yyyy年MM月";
    public static final String HM = "HHmm";
    public static final String HM_COLON = "HH:mm";
    public static final String HMS = "HHmmss";
    public static final String HMS_COLON = "HH:mm:ss";
    public static final String US_DATE = "EEE MMM dd HH:mm:ss Z yyyy";
    public static final long DAY = 24 * 60 * 60 * 1000L;

    private static final Map<String, DateFormat> DFS = new HashMap<String, DateFormat>();

    private DateUtils() {
    }

    public static int getWeekofToday() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }


    public static Calendar parse(String time) {
        Calendar calendar = Calendar.getInstance();
        int hour = Integer.valueOf(time.substring(0, time.indexOf(":")));
        int minute = Integer.valueOf(time.substring(time.indexOf(":") + 1, time.length()));
        calendar.set(1970, 1, 1, hour, minute);
        return calendar;
    }

    public static Calendar parse(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        calendar.set(1970, 1, 1, hour, minute);
        return calendar;
    }

    public static DateFormat getFormat(String pattern) {
        DateFormat format = DFS.get(pattern);
        if (format == null) {
            format = new SimpleDateFormat(pattern);
            DFS.put(pattern, format);
        }
        return format;
    }

    public static DateFormat getFormat(String pattern, Locale locale) {
        DateFormat format = DFS.get(pattern);
        if (format == null) {
            format = new SimpleDateFormat(pattern, locale);
            DFS.put(pattern, format);
        }
        return format;
    }

    public static Date parseToDate(String str) {
        if (str != null && !"".equals(str)) {
            return parse(str, YMD_DASH_WITH_TIME);
        } else {
            return null;
        }
    }

    public static Date parse(Date date) {
        if (date == null) return null;
        return parseToDate(format(date));

    }

    public static Date parse(String source, String pattern) {
        if (source == null) {
            return null;
        }
        Date date;
        try {
            date = getFormat(pattern).parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public static Date parse(String source, String pattern, Locale locale) {
        if (source == null) {
            return null;
        }
        Date date;
        try {
            date = getFormat(pattern, locale).parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return getFormat(pattern).format(date);
    }

    public static String format(Date date) {
        if (date == null) {
            return null;
        }
        return getFormat(YMD_DASH_WITH_TIME).format(date);

    }

    public static Date parseIndexDate(String source) {
        if (source == null) {
            return null;
        }
        int t = source.indexOf("T");
        int z = source.indexOf("Z");
        if (t == -1 || z == -1) {
            return null;
        }
        String s = source.substring(0, t) + " " + source.substring(t + 1, z);
        Date date;
        try {
            date = getFormat(YMD_DASH_WITH_TIME).parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public static String formatIndexDate(Date date) {
        if (date == null) {
            return null;
        }
        return getFormat(YDM_DASH).format(date) + "T" + getFormat(HMS_COLON).format(date) + "Z";
    }

    public static String formatIndexDate1(Date date) {
        if (date == null) {
            return null;
        }
        return getFormat(YMD).format(date) + "T" + getFormat(HMS).format(date) + "Z";
    }

    /**
     * @param year  年
     * @param month 月(1-12)
     * @param day   日(1-31)
     * @return 输入的年、月、日是否是有效日期
     */
    public static boolean isValid(int year, int month, int day) {
        if (month > 0 && month < 13 && day > 0 && day < 32) {
            // month of calendar is 0-based
            int mon = month - 1;
            Calendar calendar = new GregorianCalendar(year, mon, day);
            if (calendar.get(Calendar.YEAR) == year
                    && calendar.get(Calendar.MONTH) == mon
                    && calendar.get(Calendar.DAY_OF_MONTH) == day) {
                return true;
            }
        }
        return false;
    }

    private static Calendar convert(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 返回指定年数位移后的日期
     */
    public static Date yearOffset(Date date, int offset) {
        return offsetDate(date, Calendar.YEAR, offset);
    }

    /**
     * 返回指定月数位移后的日期
     */
    public static Date monthOffset(Date date, int offset) {
        return offsetDate(date, Calendar.MONTH, offset);
    }

    /**
     * 返回指定天数位移后的日期
     */
    public static Date dayOffset(Date date, int offset) {
        return offsetDate(date, Calendar.DATE, offset);
    }

    /**
     * 返回指定日期相应位移后的日期
     *
     * @param date   参考日期
     * @param field  位移单位，见 {@link Calendar}
     * @param offset 位移数量，正数表示之后的时间，负数表示之前的时间
     * @return 位移后的日期
     */
    public static Date offsetDate(Date date, int field, int offset) {
        Calendar calendar = convert(date);
        calendar.add(field, offset);
        return calendar.getTime();
    }

    /**
     * 返回当月第一天的日期
     */
    public static Date firstDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 返回当月最后一天的日期
     */
    public static Date lastDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 返回两个日期间的差异天数
     *
     * @param date1 参照日期
     * @param date2 比较日期
     * @return 参照日期与比较日期之间的天数差异，正数表示参照日期在比较日期之后，0表示两个日期同天，负数表示参照日期在比较日期之前
     */
    public static int dayDiff(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        return (int) (diff / DAY);
    }

    /**
     * 时间添加
     *
     * @param date  要修改的时间
     * @param field 要修改的字段
     * @param value 要添加的值（如是减小则传负值）
     * @return 新的时间
     */
    public static Date add(Date date, int field, int value) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(field, value);
        return gc.getTime();
    }

    /**
     * 添加或者减少日期信息
     *
     * @param date
     * @param value
     * @return
     */
    public static Date addDay(Date date, int value) {
        return add(date, Calendar.DAY_OF_MONTH, value);
    }

    /**
     * 添加或者减少日期毫秒信息
     *
     * @param date
     * @param value
     * @return
     */
    public static Date addMill(Date date, int value) {
        return add(date, Calendar.MILLISECOND, value);
    }

    public static Date addSecond(Date date, int value) {
        return add(date, Calendar.SECOND, value);
    }

    public static Date addMinute(Date date, int value) {
        return add(date, Calendar.MINUTE, value);
    }

    public static Date addMonth(Date date, int value) {
        return add(date, Calendar.MONTH, value);
    }

    public static Date addWeek(Date date, int value) {
        return add(date, Calendar.WEEK_OF_YEAR, value);
    }

    public static Date addYear(Date date, int value) {
        return add(date, Calendar.YEAR, value);
    }

    public static Calendar getCalendarTime(String dateTime) {
        Date date = DateUtils.parseToDate(dateTime);
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(date);
        return startCalendar;
    }

    public static Calendar getCalendarTime(Date dateTime) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(dateTime);
        return startCalendar;
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获取一天的最早时间
     *
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        return gc.getTime();
    }

    /**
     * 获取一天的最晚时间
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.HOUR_OF_DAY, 23);
        gc.set(Calendar.MINUTE, 59);
        gc.set(Calendar.SECOND, 59);
        return gc.getTime();
    }

    /***
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
     *
     * @param date : 时间点
     * @return
     */
    public static String getCron(Date date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    /***
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
}
