package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_SLHXLB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.wsstruts.exception.*;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 受理号序列操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtslhxl
    extends DefaultVO {

  //受理号序列信息
  private Long xlid;
  private String dwdm;
  private String slrq;
  private String slxlid;

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

  public VoXtslhxl() {
  }

  public VoXtslhxl(PoXT_SLHXLB poXtslhxlb, PoXT_DWXXB poXtdwxxb) {
    try {
      BeanUtils.copyProperties(this, poXtslhxlb);
      BeanUtils.copyProperties(this, poXtdwxxb);
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
  }

  //受理号序列
  public void setXlid(Long xlid) {
    this.xlid = xlid;
  }

  public Long getXlid() {
    return xlid;
  }

  public void setDwdm(String dwdm) {
    this.dwdm = dwdm;
  }

  public String getDwdm() {
    return dwdm;
  }

  public void setSlrq(String slrq) {
    this.slrq = slrq;
  }

  public String getSlrq() {
    return slrq;
  }

  public void setSlxlid(String slxlid) {
    this.slxlid = slxlid;
  }

  public String getSlxlid() {
    return slxlid;
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

}
