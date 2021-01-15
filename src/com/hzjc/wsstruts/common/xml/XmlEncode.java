package com.hzjc.wsstruts.common.xml;

/**
 * 定义DOM4j支持的XML编码方式
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class XmlEncode {

  /**
   *
   */
  private XmlEncode() {
  }

  //////////////////////////////////////////////////////////////////////
  // Dom4j支持的编码方式
  // Constants for supported encodings.  "external" is just a flag.
  ///////////////////////////////////////////////////////////////////////

  //////////////////////////////////////////////////////////////////////
  private final static int ENCODING_EXTERNAL = 0;
  private final static int ENCODING_UTF_8 = 1;
  private final static int ENCODING_ISO_8859_1 = 2;
  private final static int ENCODING_UCS_2_12 = 3;
  private final static int ENCODING_UCS_2_21 = 4;
  private final static int ENCODING_UCS_4_1234 = 5;
  private final static int ENCODING_UCS_4_4321 = 6;
  private final static int ENCODING_UCS_4_2143 = 7;
  private final static int ENCODING_UCS_4_3412 = 8;
  private final static int ENCODING_ASCII = 9;
  ////////////////////////////////////////////////////////////////////////

  /**
   *
   */
  public final static String ENCODEING_NAME_GBK = "GBK";
  public final static String ENCODEING_NAME_GB2312 = "GB2312";
  public final static String ENCODING_NAME_UTF_8 = "UTF-8";
  public final static String ENCODING_NAME_UTF_16 = "UTF-16";
  public final static String ENCODING_NAME_ISO_8859_1 = "ISO-8859-1";
  public final static String ENCODING_NAME_US_ASCII = "US-ASCII";
  public final static String ENCODING_NAME_UTF_16BE = "UTF-16BE";
  public final static String ENCODING_NAME_UTF_16LE = "UTF-16LE";
  public final static String ENCODING_NAME_ISO_10646_UCS_4 = "ISO-10646-UCS-4";
  public final static String ENCODING_NAME_ISO_10646_UCS_2 = "ISO-10646-UCS-2";

}