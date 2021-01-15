package com.hzjc.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.io.*;
import java.lang.*;

//封状国际化字符串的转换
public final class I18nParse {

  private I18nParse() {
  }

  //将字符串转换为GBK
  public static String  parseGBK(String strValue){
    try{
       return strValue == null ? null : (new String(strValue.getBytes("ISO8859_1"),"GBK"));
    }
    catch(Exception e){
        return null;
    }
  }

}