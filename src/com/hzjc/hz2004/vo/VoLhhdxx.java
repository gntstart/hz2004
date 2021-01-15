package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 立户户地信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoLhhdxx
    extends DefaultVO {

  private String hlx; //户类型
  private String ssxq; //省市县（区）
  private String jlx; //街路巷
  private String mlph; //门（楼）牌号
  private String mlxz; //门（楼）详址
  private String pcs; //派出所
  private String zrq; //责任区
  private String jcwh; //居（村）委会
  private String bzdzid;//标准地址ＩＤ
  private String bzdz;//标准地址名称
  private String bzdzx;
  private String bzdzy;
  private String bzdzst;

  public String getBzdzst() {
  return bzdzst;
 }
 public void setBzdzst(String bzdzst) {
   this.bzdzst = bzdzst;
 }

  public String getBzdzy() {
  return bzdzy;
 }
 public void setBzdzy(String bzdzy) {
   this.bzdzy = bzdzy;
 }

  public String getBzdzx() {
  return bzdzx;
 }
 public void setBzdzx(String bzdzx) {
   this.bzdzx = bzdzx;
 }

  public String getBzdzid() {
    return bzdzid;
  }
  public String getBzdz() {
    return bzdz;
  }

  public void setBzdzid(String bzdzid) {
    this.bzdzid = bzdzid;
  }

  public void setBzdz(String bzdz) {
    this.bzdz = bzdz;
  }

  public String getHlx() {
    return hlx;
  }

  public String getJcwh() {
    return jcwh;
  }

  public String getJlx() {
    return jlx;
  }

  public String getMlph() {
    return mlph;
  }

  public String getMlxz() {
    return mlxz;
  }

  public String getPcs() {
    return pcs;
  }

  public String getSsxq() {
    return ssxq;
  }

  public String getZrq() {
    return zrq;
  }

  public void setHlx(String hlx) {
    this.hlx = hlx;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

}
