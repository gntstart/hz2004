/* Generated by Together */

package com.hzjc.report.service;

import java.util.*;
import com.hzjc.util.StringUtils;

/**
 *
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */
public class ReportGlobalValue {
  private static final Calendar calendar = GregorianCalendar.getInstance();
  private static final int YEAR = calendar.get(Calendar.YEAR);
  private static final int MONTH = calendar.get(Calendar.MONTH) + 1;
  private static final int DAY = calendar.get(Calendar.DATE);

  private static Map mGlobalValue = null;
  private final static String GLOBALKEY_DATE = "@@DATE";
  private final static String GLOBALKEY_YEAR = "@@YEAR";
  private final static String GLOBALKEY_MONTH = "@@MONTH";
  private final static String GLOBALKEY_DAY = "@@DAY";
  //取得登陆用户的警员号--Publi-Action端传进来
  public final static String GLOBALKEY_OPERATORINT = "@@OPERATORINT";

  private static Map mDefaultValue = null;
  public final static String DEFAULTKEY_OPERATORINT = "1";
  private final static String DEFAULTKEY_DATE = "2";
  private final static String DEFAULTKEY_YEAR = "3";
  private final static String DEFAULTKEY_MONTH = "4";
  private final static String DEFAULTKEY_DAY = "5";

  //类加载初始化Map值
  static {
    //----------------------------------------------------------------------
    // 初始化全局变量
    //----------------------------------------------------------------------
    mGlobalValue = new HashMap();
    //添加全局的当前的日期值
    ////添加全局的当前年-月-日
    String strDate = StringUtils.formateDate();
    mGlobalValue.put(GLOBALKEY_DATE, strDate);
    mGlobalValue.put(GLOBALKEY_YEAR, String.valueOf(YEAR));
    mGlobalValue.put(GLOBALKEY_MONTH, String.valueOf(MONTH));
    mGlobalValue.put(GLOBALKEY_DAY, String.valueOf(DAY));

    //---------------------------------------------------------------------
    // 初始化默认变量
    //---------------------------------------------------------------------
    mDefaultValue = new HashMap();
    mDefaultValue.put(DEFAULTKEY_DATE, strDate);
    mDefaultValue.put(DEFAULTKEY_YEAR, String.valueOf(YEAR));
    mDefaultValue.put(DEFAULTKEY_MONTH, String.valueOf(MONTH));
    mDefaultValue.put(DEFAULTKEY_DAY, String.valueOf(DAY));

  }

  private ReportGlobalValue() {
  }

  /**
   * 添加新的全局值
   * @param strGlobalKey
   * @param oGlobalValue
   */
  public static void addGlobalValue(String strGlobalKey, String strGlobalValue) {
    mGlobalValue.put(strGlobalKey, strGlobalValue);
  }

  /**
   * 返回全局Map列表集合
   * @return
   */
  public static Map getGlobalMap() {
    return mGlobalValue;
  }

  /**
   * 添加新的默认值
   * @param strDefaultKey
   * @param strDefaultValue
   */
  public static void addDefaultValue(String strDefaultKey,
                                     String strDefaultValue) {
    mDefaultValue.put(strDefaultKey, strDefaultValue);
  }

  /**
   * 返回默认值Map的列表集合
   * @return
   */
  public static Map getDefaultMap() {
    return mDefaultValue;
  }

}