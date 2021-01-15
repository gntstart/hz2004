package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 证件受理信息
 */
public class VoZjslxx
    extends DefaultVO {
  private Long nbslid; //内部受理ID
  //受理信息
  private Long rynbid; //人员内部ID
  private String yxqxqsrq; //有效期限起始日
  private String yxqxjzrq; //有效期限截止日
  private String slyy; //申领原因
  private String zzlx; //制证类型
  private String lqfs; //领证方式
  private String sflx; //收费类型
  private Float sfje; //收费金额
  private Long zpid; //照片ID
  //投递信息
  private String sjrxm; //收件人姓名
  private String sjrlxdh; //收件人联系电话
  private String sjryb; //收件人邮编
  private String sjrtxdz; //收件人通讯地址

  private String zplylx;//照片来源类型(0-来源于人员照片表 / 1-来源于照片临时表 / 其它-无照片)
  private String bwbhb;

  public String getLqfs() {
    return lqfs;
  }

  public void setLqfs(String lqfs) {
    this.lqfs = lqfs;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public Float getSfje() {
    return sfje;
  }

  public String getSflx() {
    return sflx;
  }

  public String getSlyy() {
    return slyy;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public Long getZpid() {
    return zpid;
  }

  public String getZzlx() {
    return zzlx;
  }

  public void setZzlx(String zzlx) {
    this.zzlx = zzlx;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public void setSlyy(String slyy) {
    this.slyy = slyy;
  }

  public void setSflx(String sflx) {
    this.sflx = sflx;
  }

  public void setSfje(Float sfje) {
    this.sfje = sfje;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getNbslid() {
    return nbslid;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public String getZplylx() {
    return zplylx;
  }

  public void setZplylx(String zplylx) {
    this.zplylx = zplylx;
  }

  public String getSjrlxdh() {
    return sjrlxdh;
  }

  public void setSjrlxdh(String sjrlxdh) {
    this.sjrlxdh = sjrlxdh;
  }

  public String getSjrtxdz() {
    return sjrtxdz;
  }

  public void setSjrtxdz(String sjrtxdz) {
    this.sjrtxdz = sjrtxdz;
  }

  public String getSjrxm() {
    return sjrxm;
  }

  public void setSjrxm(String sjrxm) {
    this.sjrxm = sjrxm;
  }

  public String getSjryb() {
    return sjryb;
  }

  public void setSjryb(String sjryb) {
    this.sjryb = sjryb;
  }
  public String getBwbhb() {
    return bwbhb;
  }
  public void setBwbhb(String bwbhb) {
    this.bwbhb = bwbhb;
  }

}
