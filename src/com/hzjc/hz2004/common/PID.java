package com.hzjc.hz2004.common;

import java.lang.Math;

/**
 * 公民身份号码校验转换类
 * <p>Title: hz2004</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class PID {

  /**
   * 通过17位的身份号码计算得到第18位
   * @param sfhm17 - 17位身份号码
   * @return 身份号码的第18位
   */
  public static String calculateNO18ByID17(String sfhm17) {
    int power[] = new int[17];
    int result = 0;
    int result_mod = 0;
    String NO18 = new String();

    if (sfhm17 == null || (sfhm17 != null && sfhm17.length() != 17)) {
      return null;
    }

    power[0] = 7;
    power[1] = 9;
    power[2] = 10;
    power[3] = 5;
    power[4] = 8;
    power[5] = 4;
    power[6] = 2;
    power[7] = 1;
    power[8] = 6;
    power[9] = 3;
    power[10] = 7;
    power[11] = 9;
    power[12] = 10;
    power[13] = 5;
    power[14] = 8;
    power[15] = 4;
    power[16] = 2;

    for (int i = 0; i < power.length; i++) {
      result = result +
          Integer.valueOf(sfhm17.substring(i, i + 1)).intValue() * power[i];
    }

    result_mod = result % 11;
    switch (result_mod) {
      case 2:
        NO18 = "X";
        break;
      case 1:
        NO18 = "0";
        break;
      case 0:
        NO18 = "1";
        break;
      default:
        NO18 = String.valueOf(12 - result_mod);
    }

    return NO18;
  }

  /**
   * 身份号码校验
   * @param sfhm18 - 18位身份号码
   * @return
   */
  public static boolean IDCheck(String sfhm18) {

    if (sfhm18 == null || (sfhm18 != null && sfhm18.length() != 18)) {
      return false;
    }

    String sub1=sfhm18.substring(17, 18);
    String sub2=calculateNO18ByID17(sfhm18.substring(0, 17));
    if (sub1.equalsIgnoreCase(sub2)) {
      return true;
    }

    return false;
  }

  /**
   * 15位或17位身份号码转换成18位身份号码
   * @param sfhm15or17 - 15位或17位身份号码
   * @return 18位身份号码
   */
  public static String IDConver(String sfhm15or17) {
    String sfhm17 = null;
    String sfhm18 = null;

    if (sfhm15or17 == null) {
      return null;
    }

    if (sfhm15or17.length() == 15) {
      sfhm17=sfhm15or17.substring(0,6) + "19" + sfhm15or17.substring(6,15);
    }
    else if (sfhm15or17.length() == 17) {
      sfhm17=sfhm15or17;
    }

    if(sfhm17!=null){
      sfhm18=sfhm17 + calculateNO18ByID17(sfhm17);
    }

    return sfhm18;
  }

}