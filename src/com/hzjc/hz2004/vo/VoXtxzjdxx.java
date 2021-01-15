package com.hzjc.hz2004.vo;

import java.lang.reflect.*;

import org.apache.commons.beanutils.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.vo.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 乡镇街道信息操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtxzjdxx
    extends DefaultVO {

  //行政区划信息

  private String xzqhmc;


  //乡镇街道信息
  private String dm;
  private String mc;
  private String zwpy;
  private String wbpy;
  private String qhdm;
  private String bz;
  private String qybz;
  private String bdlx;
  private String bdsj;
  private String qybzmc;
  private String xdm;
  private String dzys;
  private String dzysmc;

  public VoXtxzjdxx(){
  }
  public VoXtxzjdxx(PoXT_XZJDXXB poXtxzjdxxb,PoXT_XZQHB poXtxzqhb,PoXT_XTCSB poXtcsb){
    try {
      BeanUtils.copyProperties(this, poXtxzjdxxb);
      this.setQybzmc(poXtcsb.getMc());
      this.setXzqhmc(poXtxzqhb.getMc());
    }
    catch (InvocationTargetException ex) {
       throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,ex);
    }
    catch (IllegalAccessException ex) {
       throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,ex);
    }
  }
  //行政区划

  public void setXzqhmc(String mc) {
    this.xzqhmc = mc;
  }

  public String getXzqhmc() {
    return xzqhmc;
  }

//乡镇街道信息
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
  public String getQybzmc() {
    return qybzmc;
  }

  public String getXdm() {
    return xdm;
  }

  public String getDzys() {
    return dzys;
  }

  public String getDzysmc() {
    return dzysmc;
  }

  public void setQybzmc(String qybzmc) {
    this.qybzmc = qybzmc;
  }

  public void setXdm(String xdm) {
    this.xdm = xdm;
  }

  public void setDzysmc(String dzysmc) {
    this.dzysmc = dzysmc;
  }

  public void setDzys(String dzys) {
    this.dzys = dzys;
  }

}
