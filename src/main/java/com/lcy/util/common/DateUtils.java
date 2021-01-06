package com.lcy.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author lshengda@linewell.com
 * @since 2017年6月1日
 */
public class DateUtils {

    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DATA_FORMAT = "yyyy-MM-dd";
    
    
    private static final String ZONE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss z";

    /**
     * 由日期字符串格式（yyyy-MM-dd HH:mm:ss）获取毫秒
     *
     * @param dateStr 日期字符串（yyyy-MM-dd HH:mm:ss）
     * @return 毫秒
     * @throws
     */
    public static long parseDateStrToTime(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
            long time = sdf.parse(dateStr).getTime();
            return time;
        } catch (ParseException e) {

        }
        return 0;
    }

    /**
     * 由日期字符串格式（yyyy-MM-dd）获取毫秒
     *
     * @param dateStr 日期字符串（yyyy-MM-dd）
     * @return 毫秒
     * @throws
     */
    public static long parseDateStrToTimestamp(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT);
            long time = sdf.parse(dateStr).getTime();
            return time;
        } catch (ParseException e) {

        }
        return 0;
    }

    /**
     * 由日期字符串格式（yyyy-MM-dd HH:mm:ss）获取毫秒
     *
     * @param dateStr 日期字符串（yyyy-MM-dd HH:mm:ss）
     * @return 毫秒
     * @throws ParseException
     */
    public static long parseDateTimeStrToTime(String dateStr) {

        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (date == null) {
            return 0;
        } else {
            return date.getTime();
        }
    }

    /**
     * 由毫秒获取日期字符串格式（yyyy-MM-dd HH:mm:ss）
     *
     * @param time 毫秒
     * @return 日期字符串（yyyy-MM-dd HH:mm:ss）
     * @throws
     */
    public static String parseTimeToDateStr(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return sdf.format(calendar.getTime());
    }

    /**
     * 根据时间戳转默认格式的字符串（yyyy-MM-dd HH:mm:ss）
     *
     * @param time 毫秒
     * @return 日期字符串（yyyy-MM-dd HH:mm:ss）
     */
    public static String parseTimeToDefaultStr(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        return sdf.format(time);
    }

    /**
     * 根据时间戳转默认格式的字符串（yyyy-MM-dd HH:mm:ss）
     *
     * @param time 毫秒
     * @return 日期字符串（yyyy-MM-dd HH:mm:ss）
     */
    public static String parseTimeToDate(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return sdf.format(calendar.getTime());
    }

    /**
     * 根据时间戳转字符串
     *
     * @param time    毫秒
     * @param pattern 格式
     * @return 日期字符串
     */
    public static String parseTimeToStr(long time, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(time);
    }

    /**
     * 获取一天开始时间，00:00:00 000
     */
    public static long getDayBeginTime(long time) {

        if (time == 0) {
            return time;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis();
    }

    /**
     * 获取一天结束时间，23:59:59 999
     */
    public static long getDayEndTime(long time) {

        if (time == 0) {
            return time;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTimeInMillis();
    }

    /**
	 * 返回下一个日期是不是大于上一个日期
	 * @param beforeTime
	 * @param afterTime
	 * @return
	 */
	public static int getDistanceDay(long beforeTime, long afterTime){
		return getDateByTime(afterTime)-getDateByTime(beforeTime);
	}
	
	/**
	 * 根据时间戳获取年月日(格式：20180712)
	 * @param timestamp 时间戳
	 * @return
	 */
	public static int getDateByTime(long timestamp) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1; //第一个月从0开始，所以得到月份＋1
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int dateNum = year*10000+month*100+day;

		return dateNum;
	}
    /**
     * 获取当前日期是星期几
     * 
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
    
    public static String parseZoneDateStr(String dateStr) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(ZONE_DATE_FORMAT);
			Date date = formatter.parse(dateStr);
			long time = date.getTime();
			String parseTimeToDateStr = DateUtils.parseTimeToDateStr(time);
			return parseTimeToDateStr;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }

    /**
     * 兼容所有日期格式
     * @param sFormat
     * @param sDate
     * @return
     */
    public static Date unifyDate(String sFormat, String sDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
        Date d = null;

        try {
            sdf.setLenient(false);
            d = sdf.parse(sDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }

    /**
     * 转化不同格式的时间
     * @param sDate
     * @return
     */
    public static String unifyDate(String sDate) {
        String[] sa = {
                "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd",
                "yyyyMMdd",
                "yyyy.MM.dd",
                "yyyy/MM/dd",
                "yyyy年MM月dd日"
        };

        for(String s : sa) {
            Date d = unifyDate(s, sDate);
            if(d == null) {
                continue;
            } else {
                return new SimpleDateFormat(DEFAULT_FORMAT).format(d);
            }
        }

        return "";
    }

    /**
     * 日期相差天数
     * @param date1     2019-09-23
     * @param date2     2019-09-24
     * @return  1
     */
    public static int diffDay(String date1, String date2){
        long date1TimeMills = DateUtils.parseDateStrToTime(date1 + " 00:00:00");
        long date2TimeMills = DateUtils.parseDateStrToTime(date2 + " 00:00:00");
        return  (int)((date2TimeMills - date1TimeMills) / (24 * 3600 * 1000));
    }
    
}
