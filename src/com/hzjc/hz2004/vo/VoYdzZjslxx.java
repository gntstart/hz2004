//Source file: C:\\hz2004\\com\\hzjc\\hz2004\\vo\\VoYdzZjslxx.java

package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 一代证证件受理信息
 */
public class VoYdzZjslxx
    extends DefaultVO {
  private Long rynbid; //人员内部ID
  private Long zpid; //	照片ID
  private String zz; //	住址
  private String qfrq; //签发日期
  private String yxqx; //有效期限
  private String bzlb; //办证类别
  private String bzyy; //办证原因
  private String blnf; //办理年份
  private String bzlx; //办证类型
  private String blbz; //办理标志
  private String tkzd; //特快专递

  public String getBlbz() {
    return blbz;
  }

  public String getBlnf() {
    return blnf;
  }

  public String getBzlb() {
    return bzlb;
  }

  public String getBzlx() {
    return bzlx;
  }

  public String getBzyy() {
    return bzyy;
  }

  public String getQfrq() {
    return qfrq;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public String getYxqx() {
    return yxqx;
  }

  public Long getZpid() {
    return zpid;
  }

  public String getZz() {
    return zz;
  }

  public void setZz(String zz) {
    this.zz = zz;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public void setYxqx(String yxqx) {
    this.yxqx = yxqx;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public void setQfrq(String qfrq) {
    this.qfrq = qfrq;
  }

  public void setBzyy(String bzyy) {
    this.bzyy = bzyy;
  }

  public void setBzlx(String bzlx) {
    this.bzlx = bzlx;
  }

  public void setBzlb(String bzlb) {
    this.bzlb = bzlb;
  }

  public void setBlnf(String blnf) {
    this.blnf = blnf;
  }

  public void setBlbz(String blbz) {
    this.blbz = blbz;
  }

  public String getTkzd() {
    return tkzd;
  }

  public void setTkzd(String tkzd) {
    this.tkzd = tkzd;
  }

}
