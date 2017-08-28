package com.drore.cloud.control.manger.common.base.utils;

import com.drore.cloud.control.manger.common.base.exception.CMException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.*;

/**
 * 浙江卓锐科技股份有限公司 版权所有 © Copyright 2017<br/>
 * 说明: <br/>
 * 项目名称: control-manger <br/>
 * 创建日期: 2017年07月25日 15:01 <br/>
 * 作者: <a href="6492178@gmail.com">汪萌萌</a>
 */
public class ControlDateUtils {
    /**
     * The constant YMD.
     */
    public static final String YMD = "yyyyMMdd";
    /**
     * The constant YMD_SLASH.
     */
    public static final String YMD_SLASH = "yyyy/MM/dd";
    /**
     * The constant YMD_DASH.
     */
    public static final String YMD_DASH = "yyyy-MM-dd";
    /**
     * The constant YMD_DASH_WITH_TIME.
     */
    public static final String YMD_DASH_WITH_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * The constant YMD_DASH_WITH_TIME_SIMPLE.
     */
    public static final String YMD_DASH_WITH_TIME_SIMPLE = "yyyyMMddHHmmss";
    /**
     * The constant YDM_SLASH.
     */
    public static final String YDM_SLASH = "yyyy/dd/MM";
    /**
     * The constant YDM_DASH.
     */
    public static final String YDM_DASH = "yyyy-MM-dd";
    /**
     * The constant YM_DASH.
     */
    public static final String YM_DASH = "yyyy年MM月";
    /**
     * The constant HM.
     */
    public static final String HM = "HHmm";
    /**
     * The constant HM_COLON.
     */
    public static final String HM_COLON = "HH:mm";
    /**
     * The constant HMS.
     */
    public static final String HMS = "HHmmss";
    /**
     * The constant HMS_COLON.
     */
    public static final String HMS_COLON = "HH:mm:ss";
    /**
     * The constant US_DATE.
     */
    public static final String US_DATE = "EEE MMM dd HH:mm:ss Z yyyy";
    /**
     * The constant DAY.
     */
    public static final long DAY = 24 * 60 * 60 * 1000L;

    private static final Map<String, DateTimeFormatter> DFS = new HashMap<String, DateTimeFormatter>();

    private ControlDateUtils() {
    }

    /**
     * Gets weekof today.
     *
     * @return the weekof today
     */
    public static int getWeekofToday() {
        return LocalDate.now().getDayOfWeek().getValue();
    }


    /**
     * Gets format.
     *
     * @param pattern the pattern
     * @return the format
     */
    public static DateTimeFormatter getFormat(String pattern) {
        DateTimeFormatter format = DFS.get(pattern);
        if (format == null) {
            format = DateTimeFormatter.ofPattern(pattern);
            DFS.put(pattern, format);
        }
        return format;
    }

    /**
     * Gets format.
     *
     * @param pattern the pattern
     * @param locale  the locale
     * @return the format
     */
    public static DateTimeFormatter getFormat(String pattern, Locale locale) {
        DateTimeFormatter format = DFS.get(pattern);
        if (format == null) {
            format = DateTimeFormatter.ofPattern(pattern, locale);
            DFS.put(pattern, format);
        }
        return format;
    }

    /**
     * Parse to date temporal accessor.
     *
     * @param str the str
     * @return the temporal accessor
     */
    public static TemporalAccessor parseToDate(String str) {
        if (str != null && !"".equals(str)) {
            return parse(str, YMD_DASH_WITH_TIME);
        } else {
            return null;
        }
    }

    /**
     * Parse temporal accessor.
     *
     * @param date the date
     * @return the temporal accessor
     */
    public static TemporalAccessor parse(TemporalAccessor date) {
        if (date == null) return null;
        return parseToDate(format(date));

    }

    /**
     * Parse temporal accessor.
     *
     * @param source  the source
     * @param pattern the pattern
     * @return the temporal accessor
     */
    public static TemporalAccessor parse(String source, String pattern) {
        if (source == null) {
            throw new CMException("source can not be null");
        }
        return LocalDateTime.parse(source, getFormat(pattern));
    }

    /**
     * Parse local date time.
     *
     * @param source  the source
     * @param pattern the pattern
     * @param locale  the locale
     * @return the local date time
     */
    public static LocalDateTime parse(String source, String pattern, Locale locale) {
        if (source == null) {
            throw new CMException("source can not be null");
        }
        return LocalDateTime.parse(source, getFormat(pattern, locale));
    }

    /**
     * Format string.
     *
     * @param date    the date
     * @param pattern the pattern
     * @return the string
     */
    public static String format(TemporalAccessor date, String pattern) {
        if (date == null) {
            return null;
        }
        return getFormat(pattern).format(date);
    }

    /**
     * Format string.
     *
     * @param date the date
     * @return the string
     */
    public static String format(TemporalAccessor date) {
        if (date == null) {
            return null;
        }
        return getFormat(YMD_DASH_WITH_TIME).format(date);

    }

    /**
     * Parse index date temporal accessor.
     *
     * @param source the source
     * @return the temporal accessor
     */
    public static TemporalAccessor parseIndexDate(String source) {
        if (source == null) {
            return null;
        }
        int t = source.indexOf("T");
        int z = source.indexOf("Z");
        if (t == -1 || z == -1) {
            return null;
        }
        String s = source.substring(0, t) + " " + source.substring(t + 1, z);
        return getFormat(YMD_DASH_WITH_TIME).parse(s);
    }

    /**
     * Format index date string.
     *
     * @param date the date
     * @return the string
     */
    public static String formatIndexDate(TemporalAccessor date) {
        if (date == null) {
            return null;
        }
        return getFormat(YDM_DASH).format(date) + "T" + getFormat(HMS_COLON).format(date) + "Z";
    }

    /**
     * Format index date 1 string.
     *
     * @param date the date
     * @return the string
     */
    public static String formatIndexDate1(TemporalAccessor date) {
        if (date == null) {
            return null;
        }
        return getFormat(YMD).format(date) + "T" + getFormat(HMS).format(date) + "Z";
    }

    /**
     * Is valid boolean.
     *
     * @param year  年
     * @param month 月(1-12)
     * @param day   日(1-31)
     * @return 输入的年 、月、日是否是有效日期
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


    /**
     * 返回指定年数位移后的日期
     *
     * @param date   the date
     * @param offset the offset
     * @return the date
     */
    public static LocalDate yearOffset(LocalDate date, int offset) {
        return LocalDate.from(offsetDate(date, ChronoUnit.YEARS, offset));
    }

    /**
     * 返回指定月数位移后的日期
     *
     * @param date   the date
     * @param offset the offset
     * @return the date
     */
    public static LocalDate monthOffset(LocalDate date, int offset) {
        return LocalDate.from(offsetDate(date, ChronoUnit.MONTHS, offset));
    }

    /**
     * 返回指定周数位移后的日期
     *
     * @param date   the date
     * @param offset the offset
     * @return the date
     */
    public static LocalDate weekOffset(LocalDate date, int offset) {
        return LocalDate.from(offsetDate(date, ChronoUnit.WEEKS, offset));
    }

    /**
     * 返回指定天数位移后的日期
     *
     * @param date   the date
     * @param offset the offset
     * @return the date
     */
    public static LocalDate dayOffset(LocalDate date, int offset) {
        return LocalDate.from(offsetDate(date, ChronoUnit.DAYS, offset));
    }

    /**
     * 返回指定天数位移后的日期
     *
     * @param date   the date
     * @param offset the offset
     * @return the date
     */
    public static LocalDateTime hourOffset(LocalDateTime date, int offset) {
        return LocalDateTime.from(offsetDate(date, ChronoUnit.HOURS, offset));
    }

    /**
     * 返回指定天数位移后的日期
     *
     * @param date   the date
     * @param offset the offset
     * @return the date
     */
    public static LocalDateTime minuteOffset(LocalDateTime date, int offset) {
        return LocalDateTime.from(offsetDate(date, ChronoUnit.MINUTES, offset));
    }

    /**
     * 返回指定天数位移后的日期
     *
     * @param date   the date
     * @param offset the offset
     * @return the date
     */
    public static LocalDateTime secondOffset(LocalDateTime date, int offset) {
        return LocalDateTime.from(offsetDate(date, ChronoUnit.SECONDS, offset));
    }

    /**
     * 返回指定日期相应位移后的日期
     *
     * @param date   参考日期
     * @param unit   the unit
     * @param offset 位移数量，正数表示之后的时间，负数表示之前的时间
     * @return 位移后的日期 temporal accessor
     */
    public static TemporalAccessor offsetDate(TemporalAccessor date, ChronoUnit unit, int offset) {
        if (date.isSupported(ChronoField.MINUTE_OF_DAY)) {
            return LocalDateTime.from(date).plus(offset, unit);
        } else {
            return LocalDate.from(date).plus(offset, unit);
        }
    }

    /**
     * 返回当月第一天的日期
     *
     * @param date the date
     * @return the date
     */
    public static LocalDate firstDay(LocalDate date) {
        return LocalDate.from(date).withDayOfMonth(1);
    }

    /**
     * 返回当月最后一天的日期
     *
     * @param date the date
     * @return the date
     */
    public static LocalDate lastDay(LocalDate date) {
        return LocalDate.from(date).withDayOfMonth(LocalDate.now().lengthOfMonth());
    }

    /**
     * 返回两个日期间的差异天数
     *
     * @param date1 参照日期
     * @param date2 比较日期
     * @return 参照日期与比较日期之间的天数差异 ，正数表示参照日期在比较日期之后，0表示两个日期同天，负数表示参照日期在比较日期之前
     */
    public static int dayDiff(LocalDate date1, LocalDate date2) {
        return date1.until(date2).getDays();
    }


    /***
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
     *
     * @param date : 时间点
     * @return cron cron
     */
    public static String getCron(LocalDateTime date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    /***
     * @param date the date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return string string
     */
    public static String formatDateByPattern(LocalDateTime date, String dateFormat) {
        return DateTimeFormatter.ofPattern(dateFormat).format(date);
    }

    /**
     * Date to java 8 local date time.
     *
     * @param date the date
     * @return the local date time
     */
    public static LocalDateTime dateToJava8(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Java 8 to date date.
     *
     * @param date the date
     * @return the date
     */
    public static Date Java8ToDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

}
