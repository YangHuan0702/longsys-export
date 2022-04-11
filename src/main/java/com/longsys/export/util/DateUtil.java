package com.longsys.export.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huan.yang
 * @date 2022-04-01
 */
public class DateUtil {

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String DAY = "yyyy-MM-dd";
    public static final String SUBDAY = "yyyyMMdd";
    public static final String SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String SUBSECOND = "yyyyMMddHHmmss";
    //时间长度
    public static final int LENGTH = 10;
    /**
     * 时间后缀
     */
    public static final String TIME_SUFFIX = " 00:00:00";
    final public static int MINUTE_SECOND_RATIO = 60;
    final public static int HOUR_SECOND_RATIO = 3600;
    final public static int DAY_SECOND_RATIO = 86400;
    final public static int ONE_DAY_MINUTE = 720;
    final public static int ONE_DAY_HOUR_12 = 12;
    final public static int ONE_DAY_HOUR_24 = 24;
    final public static int ONE_DAY_SECOND_12 = 3600 * 12;
    final public static int ONE_DAY_SECOND_24 = 3600 * 24;

    /**
     * date 转 string
     *
     * @return
     */
    public static String date2Str(Date date) {
        if (date == null) {
            return null;
        }
        return sf.format(date);
    }

    /**
     * date 转 String 指定日期格式
     *
     * @param date   日期类型
     * @param format 日期格式
     * @return
     */
    public static String date2Str(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * String 转 date
     *
     * @param strDate String格式日期
     * @return
     */
    public static Date str2Date(String strDate) {
        if (strDate == null) {
            return null;
        }
        Date date = null;
        try {
            date = sf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String 转 date 指定日期格式
     *
     * @param strDate String格式日期
     * @param format  日期格式
     * @return
     */
    public static Date str2Date(String strDate, String format) {
        if (strDate == null) {
            return null;
        }
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}
