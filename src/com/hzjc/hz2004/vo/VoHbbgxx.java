package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;

/**
 * 户别变更信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHbbgxx
    extends DefaultVO {

  //户别变更信息
  private Long rynbid; //人员内部ID
  private String bghhb; //变更后户别
  private String hbbglb; //户别变更类别
  private String bdfw; //变动范围
  private String hbbgrq; //户别变更日期
  private Long sbhjywid; //上笔户籍业务ID
  private String jthzl;

  //审批信息
  private Long spywid; //审批业务ID

  public String getBdfw() {
    return bdfw;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public void setBghhb(String bghhb) {
    this.bghhb = bghhb;
  }

  public String getBghhb() {
    return bghhb;
  }

  public String getHbbglb() {
    return hbbglb;
  }

  public void setHbbglb(String hbbglb) {
    this.hbbglb = hbbglb;
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

  public String getHbbgrq() {
    return hbbgrq;
  }

  public void setHbbgrq(String hbbgrq) {
    this.hbbgrq = hbbgrq;
  }

  public Long getSpywid() {
    return spywid;
  }

  public String getJthzl() {
    return jthzl;
  }

  public void setSpywid(Long spywid) {
    this.spywid = spywid;
  }

  public void setJthzl(String jthzl) {
    this.jthzl = jthzl;
  }

}
