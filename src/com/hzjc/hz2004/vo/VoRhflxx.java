package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 人户分离信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoRhflxx
    extends DefaultVO {

  private Long rynbid; //人员内部ID
  private String xzdfw;//现住地范围
  private String rhfl_ssxq; //省市县（区）
  private String rhfl_jlx; //街路巷
  private String rhfl_mlph; //门（楼）牌号
  private String rhfl_mlxz; //门（楼）详址
  private String rhfl_pcs; //派出所
  private String rhfl_zrq; //责任区
  private String rhfl_xzjd; //乡镇（街道）
  private String rhfl_jcwh; //居（村）委会
  private String bz;//备注
  private String zxyy;//注销原因
  private Long sbhjywid; //上笔户籍业务ID
  private Long rhflid;//人户分离ID
  private String rhflzt;//人户分离状态

  public String getRhfl_ssxq() {
    return rhfl_ssxq;
  }

  public void setRhfl_ssxq(String rhfl_ssxq) {
    this.rhfl_ssxq = rhfl_ssxq;
  }

  public String getRhfl_jlx() {
    return rhfl_jlx;
  }

  public void setRhfl_jlx(String rhfl_jlx) {
    this.rhfl_jlx = rhfl_jlx;
  }

  public String getRhfl_mlph() {
    return rhfl_mlph;
  }

  public void setRhfl_mlph(String rhfl_mlph) {
    this.rhfl_mlph = rhfl_mlph;
  }

  public String getRhfl_mlxz() {
    return rhfl_mlxz;
  }

  public void setRhfl_mlxz(String rhfl_mlxz) {
    this.rhfl_mlxz = rhfl_mlxz;
  }

  public String getRhfl_pcs() {
    return rhfl_pcs;
  }

  public void setRhfl_pcs(String rhfl_pcs) {
    this.rhfl_pcs = rhfl_pcs;
  }

  public String getRhfl_zrq() {
    return rhfl_zrq;
  }

  public void setRhfl_zrq(String rhfl_zrq) {
    this.rhfl_zrq = rhfl_zrq;
  }

  public String getRhfl_xzjd() {
    return rhfl_xzjd;
  }

  public void setRhfl_xzjd(String rhfl_xzjd) {
    this.rhfl_xzjd = rhfl_xzjd;
  }

  public String getRhfl_jcwh() {
    return rhfl_jcwh;
  }

  public void setRhfl_jcwh(String rhfl_jcwh) {
    this.rhfl_jcwh = rhfl_jcwh;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public String getBz() {
    return bz;
  }
  public void setBz(String bz) {
    this.bz = bz;
  }

  public Long getSbhjywid() {
    return sbhjywid;
  }
  public void setSbhjywid(Long sbhjywid) {
    this.sbhjywid = sbhjywid;
  }

  public Long getRhflid() {
    return rhflid;
  }

  public void setRhflid(Long rhflid) {
    this.rhflid = rhflid;
  }

  public String getRhflzt() {
    return rhflzt;
  }

  public void setRhflzt(String rhflzt) {
    this.rhflzt = rhflzt;
  }

  public String getXzdfw() {
    return xzdfw;
  }

  public void setXzdfw(String xzdfw) {
    this.xzdfw = xzdfw;
  }

  public String getZxyy() {
    return zxyy;
  }

  public void setZxyy(String zxyy) {
    this.zxyy = zxyy;
  }
}
