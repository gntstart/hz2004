package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 身份号码分配补录信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoSfhmfpblxx
    extends DefaultVO {

  //公民身份号码
  private String gmsfhm;

  //性别
  private String xb;

  //姓名
  private String xm;

  //单位代码
  private String dwdm;

  public String getDwdm() {
    return dwdm;
  }

  public void setDwdm(String dwdm) {
    this.dwdm = dwdm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getXb() {
    return xb;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

}
