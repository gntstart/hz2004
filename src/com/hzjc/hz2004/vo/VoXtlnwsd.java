package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_LNWSDB;
import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.wsstruts.exception.*;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 历年尾数段操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtlnwsd
    extends DefaultVO {

  //历年尾数段信息
  private Long wsdid;
  private String qhdm;
  private String dwdm;
  private String ksd;
  private String jsd;
  private String qyrq;
  private String bz;

  //单位信息
  //private String dwdm;
  private String dwmc;
  //行政区划
  private String qhmc;
  private String xzjd;

  public VoXtlnwsd() {
  }

  public VoXtlnwsd(PoXT_LNWSDB poXtlnwsdb, PoXT_XZQHB poXtxzqhb,
                   PoXT_DWXXB poXtdwxxb) {
    try {
      BeanUtils.copyProperties(this, poXtlnwsdb);
      //BeanUtils.copyProperties(this, poXtxzqhb);
      //BeanUtils.copyProperties(this, poXtdwxxb);
      this.setDwmc(poXtdwxxb.getMc());
      this.setQhmc(poXtxzqhb.getMc());
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
  }

  //历年尾数段
  public void setWsdid(Long wsdid) {
    this.wsdid = wsdid;
  }

  public Long getWsdid() {
    return wsdid;
  }

  public void setQhdm(String qhdm) {
    this.qhdm = qhdm;
  }

  public String getQhdm() {
    return qhdm;
  }

  public void setDwdm(String dwdm) {
    this.dwdm = dwdm;
  }

  public String getDwdm() {
    return dwdm;
  }

  public void setKsd(String ksd) {
    this.ksd = ksd;
  }

  public String getKsd() {
    return ksd;
  }

  public void setJsd(String jsd) {
    this.jsd = jsd;
  }

  public String getJsd() {
    return jsd;
  }

  public void setQyrq(String qyrq) {
    this.qyrq = qyrq;
  }

  public String getQyrq() {
    return qyrq;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getBz() {
    return bz;
  }

//单位信息
  public void setDwmc(String mc) {
    this.dwmc = mc;
  }

  public String getDwmc() {
    return dwmc;
  }

  //行政区划
  public void setQhmc(String mc) {
    this.qhmc = mc;
  }

  public String getQhmc() {
    return qhmc;
  }
  public String getXzjd() {
    return xzjd;
  }
  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

}
