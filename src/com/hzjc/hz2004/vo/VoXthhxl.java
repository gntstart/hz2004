package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_HHXLB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.wsstruts.exception.*;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 户号序列操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXthhxl
    extends DefaultVO {

  //户号序列信息
  private Long xlid;
  private String dwdm;
  private String hhxlid;

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

  public VoXthhxl(){
  }

  public VoXthhxl(PoXT_HHXLB poXthhxlb,PoXT_DWXXB poXtdwxxb){
    try {
      BeanUtils.copyProperties(this, poXthhxlb);
      BeanUtils.copyProperties(this, poXtdwxxb);
    }
    catch (InvocationTargetException ex) {
       throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,ex);
    }
    catch (IllegalAccessException ex) {
       throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,ex);
    }
  }
  //户号序列
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

  public void setHhxlid(String hhxlid) {
    this.hhxlid = hhxlid;
  }

  public String getHhxlid() {
    return hhxlid;
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
