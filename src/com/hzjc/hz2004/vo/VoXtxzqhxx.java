package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 行政区划操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtxzqhxx
    extends DefaultVO {
  /**
   *
   * @param poXT_MBLCXXB
   * @param poXT_SPDZB
   * @param poXT_SPMBXXB
   */
  public VoXtxzqhxx() {

  }

  public VoXtxzqhxx(PoXT_XZQHB poXT_XZQHB, PoXT_XTCSB poXT_XTCSB) {

    try {
      BeanUtils.copyProperties(this, poXT_XZQHB);
      this.setQybzmc(poXT_XTCSB.getMc());
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  //行政区划信息
  private String dm;
  private String mc;
  private String zwpy;
  private String wbpy;
  private String bz;
  private String qybz;
  private String bdlx;
  private String bdsj;

  //起用标志
  private String qybzmc;

  //行政区划信息
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

////
  public void setQybzmc(String mc) {
    this.qybzmc = mc;
  }

  public String getQybzmc() {
    return qybzmc;
  }

}
