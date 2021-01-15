package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁出注销信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQczxxx
    extends DefaultVO {

  private Long rynbid; //人员内部ID
  private String qcrq; //迁出日期
  private String qclb; //迁出类别
  private String qwdgjdq; //迁往地国家（地区）
  private String qwdssxq; //迁往地省市县（区）
  private String qwdxz; //迁往地详址
  private String qyzbh; //迁移证编号
  private String zqzbh; //准迁证编号
  private String bdfw; //变动范围
  private Long sbhjywid; //上笔户籍业务ID

  private String qyldyy;
  private String  sbrjtgx;
  private String bz; //备注
  private String zqzhyxx;
  private String cxfldm;
  public void setCxfldm(String cxfldm) {
    this.cxfldm = cxfldm;
  }

  public String getCxfldm() {
    return cxfldm;
  }

  public String getZqzhyxx() {
    return zqzhyxx;
  }

  public void setZqzhyxx(String zqzhyxx) {
    this.zqzhyxx = zqzhyxx;
  }


  public String getBdfw() {
    return bdfw;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public void setQclb(String qclb) {
    this.qclb = qclb;
  }

  public String getQclb() {
    return qclb;
  }

  public String getQcrq() {
    return qcrq;
  }

  public void setQcrq(String qcrq) {
    this.qcrq = qcrq;
  }

  public void setQwdgjdq(String qwdgjdq) {
    this.qwdgjdq = qwdgjdq;
  }

  public String getQwdgjdq() {
    return qwdgjdq;
  }

  public String getQwdssxq() {
    return qwdssxq;
  }

  public void setQwdssxq(String qwdssxq) {
    this.qwdssxq = qwdssxq;
  }

  public void setQwdxz(String qwdxz) {
    this.qwdxz = qwdxz;
  }

  public String getQwdxz() {
    return qwdxz;
  }

  public String getQyzbh() {
    return qyzbh;
  }

  public void setQyzbh(String qyzbh) {
    this.qyzbh = qyzbh;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }

  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

  public void setZqzbh(String zqzbh) {
    this.zqzbh = zqzbh;
  }

  public String getZqzbh() {
    return zqzbh;
  }
  public String getSbrjtgx() {
    return sbrjtgx;
  }
  public void setSbrjtgx(String sbrjtgx) {
    this.sbrjtgx = sbrjtgx;
   }
  public String getQyldyy() {
    return qyldyy;
  }
  public void setQyldyy(String qyldyy) {
    this.qyldyy = qyldyy;
  }
  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }


}
