/*
 * Created on 2004-8-20
 */
package com.hzjc.zzj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 日期处理工具类
 */
public class DateHelper 
{
	public static final String PRINT_YYYYMM="yyyyMM";
	public static final String EXT_PRINT_DATE_STYLE="yyyy-MM-dd";
    public static final String PRINT_DATE_STYLE="yyyy/MM/dd";
    
    public static final String PRINT_DATETIME_STYLE="yyyy/MM/dd HH:mm:ss";
    public static final String PRINT_DATETIME_STYLE2="yyyy-MM-dd HH:mm:ss";
    
    public static final String CN_DATE_STYLE="yyyy年M月d日";
    public static final String CN_HOUR_STYLE="yyyy年M月d日HH时";
    public static final String CN_MINUTE_STYLE="yyyy年M月d日HH时mm分";
    public static final String CN_SECOND_STYLE="yyyy年M月d日HH时mm分ss秒";
    public static final String CN_DATE_STYLE_WEEK="yyyy年M月d日 E";
    public static final String CN_SECOND_STYLE_ONLYTIME="HH时mm分ss秒";
 
    /**
     * 根据指定的日期字符串和格式返回日期对象
     * @param style
     * @param date
     * @return
     */    
    public static Date toDate(String date, String style) 
    {
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
     * @param 日期对象
     * @param 格式
     * @return 格式化好的日期字符串
     */
    public static String formateDate(Date dateValue, String style) 
    {
    	if(dateValue==null) return "";
    	
        if (style == null || dateValue==null)
            return null;
        SimpleDateFormat sf = new SimpleDateFormat(style);
        String strDate = sf.format(dateValue);
       
        return strDate;
    }

    /**
     * 去当前时间的格式数据
     * @param style
     * @return
     */
    public static String formateDate(String style) 
    {
        if (style == null)
            return null;
        SimpleDateFormat sf = new SimpleDateFormat(style);
        String strDate = sf.format(Calendar.getInstance().getTime());
        
        return strDate;
    }
    
    public static String formateDateYesterday(String style) 
    {
        if (style == null)
            return null;
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -1);
        
        SimpleDateFormat sf = new SimpleDateFormat(style);
        String strDate = sf.format(c.getTime());
        
        return strDate;
    }
    
    public static int getAge(int year) 
    {
        Date today = new Date(System.currentTimeMillis());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        int age = calendar.get(Calendar.YEAR) - year;
        return age;
    }
    

    
    public static Date setYear(Date date, int year)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }
    
    /**
     * 用于获取日期比较的开始时间：将指定的时间去掉时，分，秒。
     * @param date
     * @return
     */
    public static Date getStartDate(Date date){
    	if(date==null) return null;
    	
    	Calendar ca = Calendar.getInstance();
    	ca.setTime(date);
		ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH), 0,0,0);
		return ca.getTime();
    }
    
    /**
     * 用户获取日期比较的结束时间：将指定时间的时，分，秒换算成今天的最后一秒
     * @param date
     * @return
     */
    public static Date getEndDate(Date date){
    	if(date==null) return null;
    	
    	Calendar ca = Calendar.getInstance();
    	ca.setTime(date);
    	ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH), 23,59,59);
		return ca.getTime();    	
    }
    
    static public void main(String[] argc){
    	String str = DateHelper.formateDateYesterday("yyyy-MM-dd");
    	System.out.println(str);
    }
}