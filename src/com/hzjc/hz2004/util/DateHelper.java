/*
 * Created on 2004-8-20
 */
package com.hzjc.hz2004.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.time.DateFormatUtils;


/**
 * 日期处理工具类
 */
public class DateHelper {
    public static final String PRINT_YYYYMM = "yyyyMM";
    public static final String EXT_PRINT_DATE_STYLE = "yyyy-MM-dd";
    public static final String PRINT_DATE_STYLE = "yyyy/MM/dd";

    public static final String PRINT_DATETIME_STYLE = "yyyy/MM/dd HH:mm:ss";
    public static final String PRINT_DATETIME_STYLE2 = "yyyy-MM-dd HH:mm:ss";
    public static final String PRINT_DATETIME_STYLE3 = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String CN_DATE_STYLE = "yyyy年M月d日";
    public static final String CN_HOUR_STYLE = "yyyy年M月d日HH时";
    public static final String CN_MINUTE_STYLE = "yyyy年M月d日HH时mm分";
    public static final String CN_SECOND_STYLE = "yyyy年M月d日HH时mm分ss秒";
    public static final String CN_DATE_STYLE_WEEK = "yyyy年M月d日 E";
    public static final String CN_SECOND_STYLE_ONLYTIME = "HH时mm分ss秒";


    /**
     * 根据指定的日期字符串和格式返回日期对象
     *
     * @param style
     * @param date
     * @return
     */
    public static Date toDate(String date, String style) {
        if (style == null || date == null)
            return null;
        SimpleDateFormat sf = new SimpleDateFormat(style);
        try {
            return sf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按照指定的格式格式化指定的日期对象
     *
     * @param dateValue 日期对象
     * @param style     格式
     * @return 格式化好的日期字符串
     */
    public static String formateDate(Date dateValue, String style) {
        if (dateValue == null) return "";

        if (style == null || dateValue == null)
            return null;
        SimpleDateFormat sf = new SimpleDateFormat(style);
        String strDate = sf.format(dateValue);

        return strDate;
    }

    /**
     * 去当前时间的格式数据
     *
     * @param style
     * @return
     */
    public static String formateDate(String style) {
        if (style == null)
            return null;
        SimpleDateFormat sf = new SimpleDateFormat(style);
        String strDate = sf.format(Calendar.getInstance().getTime());

        return strDate;
    }

    public static Integer getAge(int year) {
        Date today = new Date(System.currentTimeMillis());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        int age = calendar.get(Calendar.YEAR) - year;
        return age;
    }

    public static Integer getAge(Date date) {
        if (date == null) return 0;
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        return getAge(year);
    }

    /**
     * 人性化显示时间
     *
     * @param d
     * @return
     */
    public static String getTimeStr(Date d) {
        if (d == null) {
            return "";
        }
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60 * 2;// 两分钟的毫秒数
        Calendar ca = Calendar.getInstance();
        long dif = ca.getTime().getTime() - d.getTime();
        if (dif < nd) {
            if (dif < nh) {
                if (dif < nm)
                    return "刚刚";
                return (dif * 2 / nm) + "分钟前";
            }
            return (dif / nh) + "小时前";
        } else {
            ca.setTime(d);
            if (ca.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
                return formateDate(d, "MM-dd");
        }
        return formateDate(d, "yyyy-MM-dd");
    }

    public static Date setYear(Date date, int year) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 用于获取日期比较的开始时间：将指定的时间去掉时，分，秒。
     *
     * @param date
     * @return
     */
    public static Date getStartDate(Date date) {
        if (date == null) return null;

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return ca.getTime();
    }
    
    public static Date getStartDate(String date) {
        if (date == null) return null;

        Date d = DateConverter.convertDate(date);
        if(d==null)
        	return null;
        
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);
        ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return ca.getTime();
    }

    public static Date getEndDate(String date) {
        if (date == null) return null;

        Date d = DateConverter.convertDate(date);
        if(d==null)
        	return null;
        
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);
        ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return ca.getTime();
    }
    
    /**
     * 用户获取日期比较的结束时间：将指定时间的时，分，秒换算成今天的最后一秒
     *
     * @param date
     * @return
     */
    public static Date getEndDate(Date date) {
        if (date == null) return null;

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return ca.getTime();
    }


    /**
     * 获取当天的开始时间
     *
     * @return
     */
    public static Date getTodayStartTime() {
        Calendar today = Calendar.getInstance(Locale.CHINA);
        today.set(Calendar.HOUR_OF_DAY,0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTime();
    }

    /**
     * 获取当天的开始时间
     *
     * @return
     */
    public static Date getTodayEndTime() {
        Calendar today = Calendar.getInstance(Locale.CHINA);
        today.set(Calendar.HOUR_OF_DAY, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);
        today.set(Calendar.MILLISECOND, 999);
        return today.getTime();
    }

    /**
     * 获取从当前到当天结束的秒数
     *
     * @return
     */
    public static int getTodayExpireTime() {
        long endTime = getTodayEndTime().getTime();
        long curTime = System.currentTimeMillis();
        return (int)(endTime - curTime) / 1000;
    }


    /**
     * 获取当前时间字符串
     * @param patten
     * @return
     */
    public static String getNowDate(String patten){
      return  DateFormatUtils.format(new Date(), patten);
    }

    public static void main(String[] arg){
       Date today= getTodayEndTime();
        System.out.print(formateDate(today,PRINT_DATETIME_STYLE2));
    }

    /**
     * 获取今天的前几天
     * @param days
     * @return
     */
    public static Date getBeforeToday(int days){
        Calendar now = Calendar.getInstance(Locale.CHINA);
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - days);
        return now.getTime();
    }

    /**
     * 获取制定日期的前几天
     * @param date
     * @param days
     * @return
     */
    public static Date getDataBefore(Date date,int days){
        Calendar now = Calendar.getInstance(Locale.CHINA);
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - days);
        return now.getTime();
    }

    /**
     * 获取今天后几天
     * @param days
     * @return
     */
    public static Date getAferToday(int days){
        Calendar now = Calendar.getInstance(Locale.CHINA);
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        return now.getTime();
    }

    /**
     * 获取制定日期后几天
     * @param date
     * @param days
     * @return
     */
    public static Date getDateAfter(Date date,int days){
        Calendar now = Calendar.getInstance(Locale.CHINA);
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        return now.getTime();
    }

    /**
     * 获取几分钟后
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date,int minutes){
        Calendar now = Calendar.getInstance(Locale.CHINA);
        now.setTime(date);
        now.add(Calendar.MINUTE, minutes);
        return now.getTime();
    }
    /**
     * 获取几分钟后
     * @param minutes
     * @return
     */
    public static Date addMinutes(int minutes){
        Calendar now = Calendar.getInstance(Locale.CHINA);
        now.add(Calendar.MINUTE, minutes);
        return now.getTime();
    }



    /**
     * 计算两个日期相差的天数
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysOfTwo(Date fDate, Date oDate) {
        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(fDate);
        caled.setTime(oDate);
        //设置时间为0时
        calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calst.set(java.util.Calendar.MINUTE, 0);
        calst.set(java.util.Calendar.SECOND, 0);
        caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
        caled.set(java.util.Calendar.MINUTE, 0);
        caled.set(java.util.Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;
        return Math.abs(days);

    }

}