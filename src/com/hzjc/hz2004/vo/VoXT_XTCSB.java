package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;
import com.hzjc.hz2004.po.PoXT_XTCSB;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 系统参数操作的入口信息</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXT_XTCSB
    extends DefaultVO {

  /**
   *
   * @param poXt_Xtcsb
   */
  public VoXT_XTCSB(PoXT_XTCSB poXt_Xtcsb) {
    super(poXt_Xtcsb);
  }

  /**
   *
   * @param strCslb
   * @param strDm
   * @param strMc
   */
  public VoXT_XTCSB(String strCslb, String strDm, String strMc) {
    this.cslb = strCslb;
    this.dm = strDm;
    this.mc = strMc;
  }

//---------------poXt_XTCSB----------------------------
  private String cslb;
  private String dm;
  private String mc;
  private String zwpy;
  private String kzbzb;
  private String kzbzc;
  private String kzbzd;
  private String kzbze;
  private String kzbzf;
  private String kzbzg;

  public String getCslb() {
    return cslb;
  }

  public String getDm() {
    return dm;
  }

  public String getKzbzb() {
    return kzbzb;
  }

  public String getKzbzc() {
    return kzbzc;
  }

  public String getKzbzd() {
    return kzbzd;
  }

  public String getKzbze() {
    return kzbze;
  }

  public String getKzbzf() {
    return kzbzf;
  }

  public String getKzbzg() {
    return kzbzg;
  }

  public String getMc() {
    return mc;
  }

  public String getZwpy() {
    return zwpy;
  }

  public void setZwpy(String zwpy) {
    this.zwpy = zwpy;
  }

  public void setMc(String mc) {
    this.mc = mc;
  }

  public void setKzbzg(String kzbzg) {
    this.kzbzg = kzbzg;
  }

  public void setKzbzf(String kzbzf) {
    this.kzbzf = kzbzf;
  }

  public void setKzbze(String kzbze) {
    this.kzbze = kzbze;
  }

  public void setKzbzd(String kzbzd) {
    this.kzbzd = kzbzd;
  }

  public void setKzbzc(String kzbzc) {
    this.kzbzc = kzbzc;
  }

  public void setKzbzb(String kzbzb) {
    this.kzbzb = kzbzb;
  }

  public void setDm(String dm) {
    this.dm = dm;
  }

  public void setCslb(String cslb) {
    this.cslb = cslb;
  }

  public String toString() {
    return getMc() == null ? "" : getMc();
  }
//-----------------end of poXt_XTCSB--------------------------
}