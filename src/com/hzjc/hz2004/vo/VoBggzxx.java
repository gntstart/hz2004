package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 变更更正信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoBggzxx
    extends DefaultVO {

  private Long rynbid; //人员内部ID
  private String bggzxm; //变更更正项目
  private String bggzhnr; //变更更正后内容
  private String zp; //照片
  private String bggzlb; //变更更正类别
  private String bggzrq; //变更更正日期
  private Long sbhjywid; //上笔户籍业务ID
  private boolean sfbczpdzplsb; //是否保存照片到照片临时表

  public VoBggzxx() {
    this.setSfbczpdzplsb(true);
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public String getBggzlb() {
    return bggzlb;
  }

  public void setBggzlb(String bggzlb) {
    this.bggzlb = bggzlb;
  }

  public String getBggzrq() {
    return bggzrq;
  }

  public void setBggzrq(String bggzrq) {
    this.bggzrq = bggzrq;
  }

  public String getBggzhnr() {
    return bggzhnr;
  }

  public void setBggzhnr(String bggzhnr) {
    this.bggzhnr = bggzhnr;
  }

  public String getBggzxm() {
    return bggzxm;
  }

  public void setBggzxm(String bggzxm) {
    this.bggzxm = bggzxm;
  }

  public String getZp() {
    return zp;
  }

  public void setZp(String zp) {
    this.zp = zp;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }

  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

  public boolean isSfbczpdzplsb() {
    return sfbczpdzplsb;
  }

  public void setSfbczpdzplsb(boolean sfbczpdzplsb) {
    this.sfbczpdzplsb = sfbczpdzplsb;
  }

}
