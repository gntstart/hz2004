package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoXT_DWXXB;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoXT_DWXXB
    extends DefaultVO {

  /**
   *
   * @param po
   */
  public VoXT_DWXXB() {

  }

  public VoXT_DWXXB(PoXT_DWXXB po) {
    super(po);
  }

  private String dm;
  private String mc;
  private String zwpy;
  private String wbpy;
  private String dwjgdm;
  private String qhdm;
  private String dwjb;
  private String bz;
  private String qybz;
  private String bdlx;
  private String bdsj;
  private String fjjgdm;
  private String fjjgmc;
  public String getBdlx() {
    return bdlx;
  }

  public String getBz() {
    return bz;
  }

  public String getBdsj() {
    return bdsj;
  }

  public String getDm() {
    return dm;
  }

  public String getDwjb() {
    return dwjb;
  }

  public String getDwjgdm() {
    return dwjgdm;
  }

  public String getMc() {
    return mc;
  }

  public String getQhdm() {
    return qhdm;
  }

  public String getQybz() {
    return qybz;
  }

  public String getWbpy() {
    return wbpy;
  }

  public String getZwpy() {
    return zwpy;
  }

  public void setZwpy(String zwpy) {
    this.zwpy = zwpy;
  }

  public void setWbpy(String wbpy) {
    this.wbpy = wbpy;
  }

  public void setQybz(String qybz) {
    this.qybz = qybz;
  }

  public void setQhdm(String qhdm) {
    this.qhdm = qhdm;
  }

  public void setMc(String mc) {
    this.mc = mc;
  }

  public void setDwjgdm(String dwjgdm) {
    this.dwjgdm = dwjgdm;
  }

  public void setDwjb(String dwjb) {
    this.dwjb = dwjb;
  }

  public void setDm(String dm) {
    this.dm = dm;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public void setBdsj(String bdsj) {
    this.bdsj = bdsj;
  }

  public void setBdlx(String bdlx) {
    this.bdlx = bdlx;
  }
  public String getFjjgdm() {
    return fjjgdm;
  }
  public void setFjjgdm(String fjjgdm) {
    this.fjjgdm = fjjgdm;
  }
  public String getFjjgmc() {
    return fjjgmc;
  }
  public void setFjjgmc(String fjjgmc) {
    this.fjjgmc = fjjgmc;
  }

}
