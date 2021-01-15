package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

public class VoZwTjxx  extends DefaultVO {
  public VoZwTjxx() {
  }

  private Long ryid;//人员ID
  private String gmsfhm;//公民身份号码
  private String xm;//姓名
  private String xb;//性别
  private String zwbm;//指纹编码
  private String zwtzsj;//指纹特征数据
  private float txzlz;//图像质量值
  private String zwzcjg;//指纹注册结果
  private String zwcjqid;//指纹采集器ID
  private String zwcjqdm;//指纹采集器代码
  private String zwsfbbh;//指纹算法版本号
  private String zwsfkfzdm;//指纹算法开发者代码
  private String zwtx;//指纹图像

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXm() {
    return xm;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXb() {
    return xb;
  }

  public void setZwbm(String zwbm) {
    this.zwbm = zwbm;
  }

  public String getZwbm() {
    return zwbm;
  }

  public void setZwtzsj(String zwtzsj) {
    this.zwtzsj = zwtzsj;
  }

  public String getZwtzsj() {
    return zwtzsj;
  }

  public void setTxzlz(float txzlz) {
    this.txzlz = txzlz;
  }

  public float getTxzlz() {
    return txzlz;
  }

  public void setZwzcjg(String zwzcjg) {
    this.zwzcjg = zwzcjg;
  }

  public String getZwzcjg() {
    return zwzcjg;
  }

  public void setZwcjqid(String zwcjqid) {
    this.zwcjqid = zwcjqid;
  }

  public String getZwcjqid() {
    return zwcjqid;
  }

  public void setZwcjqdm(String zwcjqdm) {
    this.zwcjqdm = zwcjqdm;
  }

  public String getZwcjqdm() {
    return zwcjqdm;
  }

  public void setZwsfbbh(String zwsfbbh) {
    this.zwsfbbh = zwsfbbh;
  }

  public String getZwsfbbh() {
    return zwsfbbh;
  }

  public void setZwsfkfzdm(String zwsfkfzdm) {
    this.zwsfkfzdm = zwsfkfzdm;
  }

  public String getZwsfkfzdm() {
    return zwsfkfzdm;
  }

  public void setZwtx(String zwtx) {
    this.zwtx = zwtx;
  }

  public String getZwtx() {
    return zwtx;
  }

}
