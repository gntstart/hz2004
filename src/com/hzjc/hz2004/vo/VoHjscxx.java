package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 户籍删除信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHjscxx
    extends DefaultVO {
  //户籍删除信息
  private Long rynbid; //人员内部ID
  private String qcrq; //迁出日期
  private String qwdgjdq; //迁往地国家（地区）
  private String qwdssxq; //迁往地省市县（区）
  private String qwdxz; //迁往地详址
  private String qyzbh; //迁移证编号
  private String zqzbh; //准迁证编号
  private String swrq; //死亡日期
  private String swnl; //死亡年龄
  private String swzmbh; //死亡证明编号
  private String hjsclb; //户籍删除类别
  private String bdfw; //变动范围
  private Long sbhjywid; //上笔户籍业务ID
  private String hjscsm;//户籍删除说明
  //审批信息
  private Long spywid; //审批业务ID

  public String getBdfw() {
    return bdfw;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public String getHjsclb() {
    return hjsclb;
  }

  public void setHjsclb(String hjsclb) {
    this.hjsclb = hjsclb;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }

  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

  public String getQcrq() {
    return qcrq;
  }

  public void setQcrq(String qcrq) {
    this.qcrq = qcrq;
  }

  public String getQwdgjdq() {
    return qwdgjdq;
  }

  public void setQwdgjdq(String qwdgjdq) {
    this.qwdgjdq = qwdgjdq;
  }

  public String getQwdssxq() {
    return qwdssxq;
  }

  public void setQwdssxq(String qwdssxq) {
    this.qwdssxq = qwdssxq;
  }

  public String getQwdxz() {
    return qwdxz;
  }

  public void setQwdxz(String qwdxz) {
    this.qwdxz = qwdxz;
  }

  public String getQyzbh() {
    return qyzbh;
  }

  public void setQyzbh(String qyzbh) {
    this.qyzbh = qyzbh;
  }

  public String getSwnl() {
    return swnl;
  }

  public void setSwnl(String swnl) {
    this.swnl = swnl;
  }

  public String getSwrq() {
    return swrq;
  }

  public void setSwrq(String swrq) {
    this.swrq = swrq;
  }

  public String getSwzmbh() {
    return swzmbh;
  }

  public void setSwzmbh(String swzmbh) {
    this.swzmbh = swzmbh;
  }

  public String getZqzbh() {
    return zqzbh;
  }

  public void setZqzbh(String zqzbh) {
    this.zqzbh = zqzbh;
  }

  public Long getSpywid() {
    return spywid;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public String getHjscsm() {
    return hjscsm;
  }

  public void setHjscsm(String hjscsm) {
    this.hjscsm = hjscsm;
  }

}
