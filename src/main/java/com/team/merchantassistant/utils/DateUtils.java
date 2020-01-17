package com.team.merchantassistant.utils;

/**
 * @author mai
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils {



    /**
     * 根据年月获得该月的第一天
     *
     * @param date
     * @return
     */
    public static String getFirstDayOfMonth(String date) {
        String[] value = date.split("-");
        int year = Integer.parseInt(value[0]);
        int month = Integer.parseInt(value[1]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 根据年月获得该月的最后一天
     *
     * @param date
     * @return
     */
    public static String getLastDayOfMonth(String date) {
        String[] value = date.split("-");
        int year = Integer.parseInt(value[0]);
        int month = Integer.parseInt(value[1]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }


    /**
     * 根据年获得该年的第一天
     *
     * @param date
     * @return
     */
    public static String getFirstDayOfYear(String date) {
        int year = Integer.parseInt(date);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, cal.getMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 根据年获得该年的最后一天
     *
     * @param date
     * @return
     */
    public static String getLastDayOfYear(String date) {
        int year = Integer.parseInt(date);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }



    /**
     * 自定义昨天的时间格式
     *
     * @return String
     */
    public static String getYesterdayMe() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        return new SimpleDateFormat("M月d日").format(time);
    }

    /**
     * 自定义获取本周的第一天时间格式
     *
     * @return String
     **/
    public static String getWeekStartMe() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time = cal.getTime();
        return new SimpleDateFormat("M月d日").format(time);
    }

    /**
     * 自定义本周的最后一天时间格式
     *
     * @return String
     **/
    public static String getWeekEndMe() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("M月d日").format(time);
    }

    /**
     * 自定义本月开始日期时间格式
     *
     * @return String
     **/
    public static String getMonthStartMe() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("M月d日").format(time);
    }

    /**
     * 自定义本月最后一天时间格式
     *
     * @return String
     **/
    public static String getMonthEndMe() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = cal.getTime();
        return new SimpleDateFormat("M月d日").format(time);
    }

    /**
     * 自定义本年的第一天时间格式
     *
     * @return String
     **/
    public static String getYearStartMe() {
        return "1月1日";
    }

    /**
     * 自定义本年的最后一天时间格式
     *
     * @return String
     **/
    public static String getYearEndMe() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("M月d日").format(currYearLast);
    }

    /**
     * 获取今天
     *
     * @return String
     */
    public static String getToday() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取昨天
     *
     * @return String
     */
    public static String getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本月开始日期
     *
     * @return String
     **/
    public static String getMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本月最后一天
     *
     * @return String
     **/
    public static String getMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本周的第一天
     *
     * @return String
     **/
    public static String getWeekStart() {
        Calendar cal = Calendar.getInstance();
        //设置一周开始的时间为周一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本周的最后一天
     *
     * @return String
     **/
    public static String getWeekEnd() {
        Calendar cal = Calendar.getInstance();
        //设置一周开始的时间为周一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 获取本年的第一天
     *
     * @return String
     **/
    public static String getYearStart() {
        return new SimpleDateFormat("yyyy").format(new Date()) + "-01-01";
    }

    /**
     * 获取本年的最后一天
     *
     * @return String
     **/
    public static String getYearEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currYearLast);
    }
}