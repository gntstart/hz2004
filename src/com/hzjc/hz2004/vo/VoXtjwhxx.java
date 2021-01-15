package com.hzjc.hz2004.vo;

import java.lang.reflect.*;

import org.apache.commons.beanutils.*;
import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.exception.*;
import com.hzjc.wsstruts.vo.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 居委会信息操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtjwhxx
    extends DefaultVO {

  private String dwmc;
  private String xzjdmc;

  //居委会信息
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
  private String qybzmc;
  private String cxfldm;
  private String cxflmc;
  private String tjdm;
  private String tjmc;
  private String xdm;
  private String dzys;
  private String dzysmc;
  private String hzzpid;
  private String qmzpid;
  public void setCxfldm(String cxfldm) {
    this.cxfldm = cxfldm;
  }

  public String getCxfldm() {
    return cxfldm;
  }

  public void setCxflmc(String cxflmc) {
    this.cxflmc = cxflmc;
  }

  public String getCxflmc() {
    return cxflmc;
  }

  public void setTjdm(String tjdm) {
    this.tjdm = tjdm;
  }

  public String getTjdm() {
    return tjdm;
  }

  public void setTjmc(String tjmc) {
    this.tjmc = tjmc;
  }

  public String getTjmc() {
    return tjmc;
  }

  public VoXtjwhxx() {
  }

  public VoXtjwhxx(PoXT_JWHXXB poXtjwhxxb, PoXT_DWXXB poXtdwxxb,
                   PoXT_XZJDXXB poXtxzjdb,
                   PoXT_XTCSB poXtcsb) {
    try {
      BeanUtils.copyProperties(this, poXtjwhxxb);
      this.setQybzmc(poXtcsb.getMc());
      this.setXzjdmc(poXtxzjdb.getMc());
      this.setDwmc(poXtdwxxb.getMc());
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
  }

  //名称

  public void setDwmc(String mc) {
    this.dwmc = mc;
  }

  public String getDwmc() {
    return dwmc;
  }

  public void setXzjdmc(String mc) {
    this.xzjdmc = mc;
  }

  public String getXzjdmc() {
    return xzjdmc;
  }

//居委会信息
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

  public String getQybzmc() {
    return qybzmc;
  }

  public String getDzys() {
    return dzys;
  }

  public String getDzysmc() {
    return dzysmc;
  }

  public String getXdm() {
    return xdm;
  }

  public void setQybzmc(String qybzmc) {
    this.qybzmc = qybzmc;
  }

  public void setDzys(String dzys) {
    this.dzys = dzys;
  }

  public void setDzysmc(String dzysmc) {
    this.dzysmc = dzysmc;
  }

  public void setXdm(String xdm) {
    this.xdm = xdm;
  }

	public String getHzzpid() {
		return hzzpid;
	}
	
	public void setHzzpid(String hzzpid) {
		this.hzzpid = hzzpid;
	}
	
	public String getQmzpid() {
		return qmzpid;
	}
	
	public void setQmzpid(String qmzpid) {
		this.qmzpid = qmzpid;
	}

}
