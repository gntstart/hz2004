package com.hzjc.hz2004.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.text.ParseException;


/**
* 
* 日期转换对象，使用该转换器，在BaseForm当中做一下注册，系统自动地帮助字符的日期表示转换为java.util.Date对象.
* @author zizz.
* Create Time：2006-9-11 19:11:47.
*/
public class DateConverter {
    /**
     * 模式集合.
     */
    private static List<String> patterns = new ArrayList<String>();
    
    //注册日期的转换格式
    static{
    	DateConverter.patterns.add("yyyy-MM-dd HH:mm:ss.SSS");
        DateConverter.patterns.add("yyyy-MM-dd HH:mm:ss");
        DateConverter.patterns.add("yyyy-MM-dd HH:mm");
        DateConverter.patterns.add("yyyy-MM-dd");
        
        DateConverter.patterns.add("yyyy/MM/dd HH:mm:ss");
        DateConverter.patterns.add("yyyy/MM/dd HH:mm");
        DateConverter.patterns.add("yyyy/MM/dd");
        
        DateConverter.patterns.add("yyyy年MM月dd日 HH:mm:ss");
        DateConverter.patterns.add("yyyy年MM月dd日 HH:mm");
        DateConverter.patterns.add("yyyy年MM月dd日");
    }
    
    public static Date convertDateByFormat(String sdate,String format){
    	if(CommonUtil.isEmpty(sdate))
    		return null;
    	
    	SimpleDateFormat df = new SimpleDateFormat();
    	df.applyPattern(format);
    	try{
    		return df.parse((String)sdate);
    	}catch(Exception e){
    		return convertDate(sdate);
    	}
    }
    
    /**
     * 日期转换器.
     * @param type Class
     * @param value Object
     * return Date Object.
     */
	public Date convert(Class<?> type,Object value){
        SimpleDateFormat df = new SimpleDateFormat();
        
        if(value == null){
            return null;
        }else if(value instanceof String){
            Object dateObj = null;
            Iterator<?> it = patterns.iterator();
            while(it.hasNext()){
                try{
                    String pattern = (String)it.next();
                    df.applyPattern(pattern);
                    dateObj = df.parse((String)value);
                    break;
                }catch(ParseException ex){
                    ;
                }
            }
            return (Date)dateObj;
        }else{
            return null;
        }
    }
    
	static public Date convertDate(Object value){
        SimpleDateFormat df = new SimpleDateFormat();
        
        if(value == null || value.toString().equals("")){
            return null;
        }else if(value instanceof String){
            Date dateObj = null;
            for(int i=0;i<patterns.size();i++){
            	 try{
                     String pattern = patterns.get(i);
                     df.applyPattern(pattern);
                     dateObj = df.parse((String)value);
                     break;
                 }catch(ParseException ex){
                     ;
                 }
            }
            return dateObj;
        }else{
            return null;
        }
    }

    /**
     * 将Date转换为指定格式的时间字符串，如YYYYMMDDhhmmss或yyyy-MM-dd HH:mm
     *
     * @param time
     * @return
     */
    public static String parseDateToStr(Date time, String timeFromat) {
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }
}

