//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoZjslfhxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件受理返回信息
 */
public class VoZjslfhxx
    extends DefaultVO {

  private Long nbslid; //内部受理ID
  private Long hhnbid; //户号内部ID
  private Long mlpnbid; //门楼牌内部ID
  private Long rynbid; //人员内部ID
  private Long ryid; //人员ID
  private String gmsfhm; //公民身份号码
  private String xm; //姓名

  public Long getNbslid() {
    return nbslid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

}
