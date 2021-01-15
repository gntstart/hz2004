//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjgsxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件挂失信息
 */
public class VoZjgsxx
    extends DefaultVO {
  private Long nbsfzid; //内部身份证ID
  private String ysdd; //遗失地点
  private String ysrq; //遗失日期
  private String gsrxm; //挂失人姓名
  private String gsrgmsfhm; //挂失人公民身份号码

  public String getGsrgmsfhm() {
    return gsrgmsfhm;
  }

  public String getGsrxm() {
    return gsrxm;
  }

  public Long getNbsfzid() {
    return nbsfzid;
  }

  public String getYsdd() {
    return ysdd;
  }

  public String getYsrq() {
    return ysrq;
  }

  public void setYsrq(String ysrq) {
    this.ysrq = ysrq;
  }

  public void setYsdd(String ysdd) {
    this.ysdd = ysdd;
  }

  public void setNbsfzid(Long nbsfzid) {
    this.nbsfzid = nbsfzid;
  }

  public void setGsrxm(String gsrxm) {
    this.gsrxm = gsrxm;
  }

  public void setGsrgmsfhm(String gsrgmsfhm) {
    this.gsrgmsfhm = gsrgmsfhm;
  }

}
