package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 用户居委会信息操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtyhjwh
    extends DefaultVO {
  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtyhjwh() {
  }

  public VoXtyhjwh(String sYhid, String sXqlx, PoXT_JWHXXB XT_JWHXXB) {

    try {
      this.setYhid(sYhid);
      this.setXqlx(sXqlx);
      BeanUtils.copyProperties(this, XT_JWHXXB);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  private String yhid;
  private String xqlx;
  //用户居委会信息
  private String dm;
  private String mc;
  private String zwpy;
  private String wbpy;
  private String dwdm;
  private String xzjddm;
  private String bz;
  private String qybz;
  private String bdlx;
  private String bdsj;

  public void setYhid(String yhid) {
    this.yhid = yhid;
  }

  public String getYhid() {
    return this.yhid;
  }

  public void setXqlx(String xqlx) {
    this.xqlx = xqlx;
  }

  public String getXqlx() {
    return this.xqlx;
  }

  //用户居委会信息
  public void setDm(String dm) {
    this.dm = dm;
  }

  public String getDm() {
    return dm;
  }

  public void setMc(String mc) {
    this.mc = mc;
  }

  public String getMc() {
    return mc;
  }

  public void setZwpy(String zwpy) {
    this.zwpy = zwpy;
  }

  public String getZwpy() {
    return zwpy;
  }

  public void setWbpy(String wbpy) {
    this.wbpy = wbpy;
  }

  public String getWbpy() {
    return wbpy;
  }

  public void setDwdm(String dwdm) {
    this.dwdm = dwdm;
  }

  public String getDwdm() {
    return dwdm;
  }

  public void setXzjddm(String xzjddm) {
    this.xzjddm = xzjddm;
  }

  public String getXzjddm() {
    return xzjddm;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getBz() {
    return bz;
  }

  public void setQybz(String qybz) {
    this.qybz = qybz;
  }

  public String getQybz() {
    return qybz;
  }

  public void setBdlx(String bdlx) {
    this.bdlx = bdlx;
  }

  public String getBdlx() {
    return bdlx;
  }

  public void setBdsj(String bdsj) {
    this.bdsj = bdsj;
  }

  public String getBdsj() {
    return bdsj;
  }

}
