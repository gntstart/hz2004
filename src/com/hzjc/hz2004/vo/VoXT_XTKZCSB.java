package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;
import com.hzjc.hz2004.po.*;

/**
 * 系统控制参数VO
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoXT_XTKZCSB
    extends DefaultVO {

  /**
   *
   */
  public VoXT_XTKZCSB() {
  }

  /**
   *
   * @param po
   */
  public VoXT_XTKZCSB(PoXT_XTKZCSB po) {
    super(po);
  }

  private Long csid;
  private String kzlb;
  private String kzmc;
  private String kzz;
  private String mrz;
  private String bz;
  private String xgbz;
  private String bdlx;
  private String bdsj;
  public String getBdlx() {
    return bdlx;
  }

  public String getBdsj() {
    return bdsj;
  }

  public String getBz() {
    return bz;
  }

  public String getKzlb() {
    return kzlb;
  }

  public Long getCsid() {
    return csid;
  }

  public String getKzmc() {
    return kzmc;
  }

  public String getKzz() {
    return kzz;
  }

  public String getMrz() {
    return mrz;
  }

  public String getXgbz() {
    return xgbz;
  }

  public void setXgbz(String xgbz) {
    this.xgbz = xgbz;
  }

  public void setMrz(String mrz) {
    this.mrz = mrz;
  }

  public void setKzz(String kzz) {
    this.kzz = kzz;
  }

  public void setKzmc(String kzmc) {
    this.kzmc = kzmc;
  }

  public void setKzlb(String kzlb) {
    this.kzlb = kzlb;
  }

  public void setCsid(Long csid) {
    this.csid = csid;
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

}