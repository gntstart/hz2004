package com.hzjc.hz2004.vo;

public class VoRybdxx {
  public VoRybdxx() {
  }

  private Long bdbid; //变动表ID
  private String bdyy; //变动原因
  private String bdfw; //变动范围
  private String bdrq; //变动日期
  private Long rynbid; //人员内部ID
  private Long bdq_hhnbid; //变动前户号内部ID
  private Long bdh_hhnbid; //变动后户号内部ID
  //迁出
  private String qwdssxq; //迁往地省市县区
  private String qwdxz; //迁往地详址
  //迁入
  private String qcdssxq; //迁出地省市县区
  private String qcdxz; //迁出地详址

  private String bdqhb; //变动前户别

  private Long rzjs; //人增减数
  private Long hzjs; //户增减数

  public Long getBdbid() {
    return bdbid;
  }

  public void setBdbid(Long bdbid) {
    this.bdbid = bdbid;
  }

  public String getBdfw() {
    return bdfw;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public String getBdyy() {
    return bdyy;
  }

  public void setBdyy(String bdyy) {
    this.bdyy = bdyy;
  }

  public String getBdrq() {
    return bdrq;
  }

  public void setBdrq(String bdrq) {
    this.bdrq = bdrq;
  }

  public String getQcdssxq() {
    return qcdssxq;
  }

  public void setQcdssxq(String qcdssxq) {
    this.qcdssxq = qcdssxq;
  }

  public String getQcdxz() {
    return qcdxz;
  }

  public void setQcdxz(String qcdxz) {
    this.qcdxz = qcdxz;
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

  public Long getBdh_hhnbid() {
    return bdh_hhnbid;
  }

  public void setBdh_hhnbid(Long bdh_hhnbid) {
    this.bdh_hhnbid = bdh_hhnbid;
  }

  public Long getBdq_hhnbid() {
    return bdq_hhnbid;
  }

  public void setBdq_hhnbid(Long bdq_hhnbid) {
    this.bdq_hhnbid = bdq_hhnbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getHzjs() {
    return hzjs;
  }

  public void setHzjs(Long hzjs) {
    this.hzjs = hzjs;
  }

  public Long getRzjs() {
    return rzjs;
  }

  public void setRzjs(Long rzjs) {
    this.rzjs = rzjs;
  }

  public String getBdqhb() {
    return bdqhb;
  }

  public void setBdqhb(String bdqhb) {
    this.bdqhb = bdqhb;
  }

}