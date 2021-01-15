package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.wsstruts.exception.*;
import java.lang.reflect.*;
import com.hzjc.hz2004.appbase.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 单位信息操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtdwxx
    extends DefaultVO {

  //行政区划信息

  private String xzqhmc;

  //单位信息
  private String dm;
  private String mc;
  private String zwpy;
  private String wbpy;
  private String dwjgdm;
  private String qhdm;
  private String bz;
  private String qybz;
  private String bdlx;
  private String bdsj;
  private String dwjb;

  private String qybzmc;
  private String dwjbmc;
  private String fjjgdm;
  private String fjjgmc;

  public VoXtdwxx() {
  }

  public VoXtdwxx(PoXT_DWXXB poXtdwxxb
                  , PoXT_XZQHB poXtxzqhb, PoXT_XTCSB poXtcsba,
                  PoXT_XTCSB poXtcsbb
                  ) {
    try {
      BeanUtils.copyProperties(this, poXtdwxxb);

      //this.setXzqhmc(SysParam.getXt_xtcsbAsMap(SysParam.DM_XZQHB).get(poXtdwxxb.getQhdm()).toString());
      //this.setQybzmc(SysParam.getXt_xtcsbAsMap(SysParam.DM_QYBZ).get(poXtdwxxb.getQybz()).toString());
      //this.setDwjbmc(SysParam.getXt_xtcsbAsMap(SysParam.DM_DWJB).get(poXtdwxxb.getDwjb()).toString());

      this.setXzqhmc(poXtxzqhb.getMc());
      this.setQybzmc(poXtcsbb.getMc());
      this.setDwjbmc(poXtcsba.getMc());
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
  }

  //行政区划

  public void setXzqhmc(String mc) {
    this.xzqhmc = mc;
  }

  public String getXzqhmc() {
    return xzqhmc;
  }

//单位信息
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

  public void setDwjgdm(String dwjgdm) {
    this.dwjgdm = dwjgdm;
  }

  public String getDwjgdm() {
    return dwjgdm;
  }

  public void setQhdm(String qhdm) {
    this.qhdm = qhdm;
  }

  public String getQhdm() {
    return qhdm;
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

  public String getDwjb() {
    return dwjb;
  }

  public void setDwjb(String dwjb) {
    this.dwjb = dwjb;
  }

  //
  public String getQybzmc() {
    return this.qybzmc;
  }

  public void setQybzmc(String mc) {
    this.qybzmc = mc;
  }

  public String getDwjbmc() {
    return this.dwjbmc;
  }

  public void setDwjbmc(String mc) {
    this.dwjbmc = mc;
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
