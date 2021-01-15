package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 公民身份号码分配申请信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@mail.com
 * @version 1.0
 */
public class VoGmsfhmfpsqxx
    extends DefaultVO {

  private Long ryid; //人员ID
  private String xm; //姓名
  private String xb; //性别
  private String csrq; //出生日期
  private String xzqh; //行政区划
  private String pcs; //派出所
  private String xzjd; //乡镇街道

  public Long getRyid() {
    return ryid;
  }

  public String getXb() {
    return xb;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public String getXzqh() {
    return xzqh;
  }

  public void setXzqh(String xzqh) {
    this.xzqh = xzqh;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getPcs() {
    return pcs;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

}