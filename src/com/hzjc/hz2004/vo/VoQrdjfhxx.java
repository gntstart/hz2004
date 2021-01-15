package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 迁入登记返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoQrdjfhxx
    extends DefaultVO {

  private Long sys_bh; //系统编号
  private Long qrdjid; //迁入登记ID
  private Long rynbid; //人员内部ID
  private Long ryid; //人员ID
  private Long hhnbid; //户号内部ID
  private String xm; //姓名
  private String gmsfhm; //公民身份号码
  private String yhzgx; //与户主关系

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getXm() {
    return xm;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public Long getQrdjid() {
    return qrdjid;
  }

  public void setQrdjid(Long qrdjid) {
    this.qrdjid = qrdjid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getSys_bh() {
    return sys_bh;
  }

  public void setSys_bh(Long sys_bh) {
    this.sys_bh = sys_bh;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public String getYhzgx() {
    return yhzgx;
  }

  public void setYhzgx(String yhzgx) {
    this.yhzgx = yhzgx;
  }

}
