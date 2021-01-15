//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjlqffxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件领取发放信息
 */
public class VoZjlqffxx
    extends DefaultVO {
  private Long nbslid; //内部受理ID
  private String lqfs; //领取发放类别
  private String lqrq; //领取日期
  private String lqrxm; //领取人姓名
  private String lqrsfhm; //领取人身份号码
  private String zjddrq;//证件到达日期

  public String getLqrq() {
    return lqrq;
  }

  public String getLqrsfhm() {
    return lqrsfhm;
  }

  public String getLqrxm() {
    return lqrxm;
  }

  public String getZjddrq() {
    return zjddrq;
  }

  public Long getNbslid() {
    return nbslid;
  }

  public void setZjddrq(String zjddrq) {
    this.zjddrq = zjddrq;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public void setLqrxm(String lqrxm) {
    this.lqrxm = lqrxm;
  }

  public void setLqrsfhm(String lqrsfhm) {
    this.lqrsfhm = lqrsfhm;
  }

  public void setLqrq(String lqrq) {
    this.lqrq = lqrq;
  }

  public void setLqfs(String lqfs) {
    this.lqfs = lqfs;
  }

  public String getLqfs() {
    return lqfs;
  }

}
