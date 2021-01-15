package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_JLXXXB;
import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.hz2004.po.PoXT_JLXJWHDZXXB;
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

public class VoXtjlxjwhdz
    extends DefaultVO {

  //街路巷居委会对照信息
  private Long czid;
  private String jlxdm;
  private String jwhdm;
  private String jlxhlx;
  private String jlxh;
  private String qybz;
  private String bdlx;
  private String bdsj;

  //街路巷信息
  private String jlxmc;
  private String jlxzwpy;
  private String jlxwbpy;
  //居委会信息
  private String jwhmc;
  private String qybzmc;
  private String jlxhlxmc;
  private String jwhzwpy;
  private String jwhwbpy;

  public VoXtjlxjwhdz() {
  }

  public VoXtjlxjwhdz(PoXT_JLXJWHDZXXB poXTJLXJWHDZXXB, PoXT_JLXXXB poXTJLXXXB,
                      PoXT_JWHXXB poXTJWHXXB, PoXT_XTCSB poXTCSBA,
                      PoXT_XTCSB poXTCSBB) {
    try {
      BeanUtils.copyProperties(this, poXTJLXJWHDZXXB);
      if (poXTCSBA != null) {
        this.setQybzmc(poXTCSBA.getMc());
      }
      if (poXTCSBB != null) {
        this.setJlxhlxmc(poXTCSBB.getMc());
      }
      this.setJlxmc(poXTJLXXXB.getMc());
      this.setJlxzwpy(poXTJLXXXB.getZwpy());
      this.setJlxwbpy(poXTJLXXXB.getWbpy());
      this.setJwhmc(poXTJWHXXB.getMc());
      this.setJwhwbpy(poXTJWHXXB.getWbpy());
      this.setJwhzwpy(poXTJWHXXB.getZwpy());
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
  }

  //街路巷居委会对照信息
  public void setCzid(Long czid) {
    this.czid = czid;
  }

  public Long getCzid() {
    return czid;
  }

  public void setJlxdm(String jlxdm) {
    this.jlxdm = jlxdm;
  }

  public String getJlxdm() {
    return jlxdm;
  }

  public void setJwhdm(String jwhdm) {
    this.jwhdm = jwhdm;
  }

  public String getJwhdm() {
    return jwhdm;
  }

  public void setJlxhlx(String jlxhlx) {
    this.jlxhlx = jlxhlx;
  }

  public String getJlxhlx() {
    return jlxhlx;
  }

  public void setJlxh(String jlxh) {
    this.jlxh = jlxh;
  }

  public String getJlxh() {
    return jlxh;
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

  //街路巷信息
  public void setJlxmc(String mc) {
    this.jlxmc = mc;
  }

  public String getJlxmc() {
    return jlxmc;
  }

  public void setJlxzwpy(String zwpy) {
    this.jlxzwpy = zwpy;
  }

  public String getJlxzwpy() {
    return jlxzwpy;
  }

  public void setJlxwbpy(String wbpy) {
    this.jlxwbpy = wbpy;
  }

  public String getJlxwbpy() {
    return jlxwbpy;
  }

  //居委会信息
  public void setJwhmc(String mc) {
    this.jwhmc = mc;
  }

  public String getJwhmc() {
    return jwhmc;
  }

  public String getQybzmc() {
    return qybzmc;
  }

  public void setQybzmc(String qybzmc) {
    this.qybzmc = qybzmc;
  }

  public String getJlxhlxmc() {
    return jlxhlxmc;
  }

  public void setJlxhlxmc(String jlxhlxmc) {
    this.jlxhlxmc = jlxhlxmc;
  }

  public String getJwhzwpy() {
    return jwhzwpy;
  }

  public void setJwhzwpy(String jwhzwpy) {
    this.jwhzwpy = jwhzwpy;
  }

  public String getJwhwbpy() {
    return jwhwbpy;
  }

  public void setJwhwbpy(String jwhwbpy) {
    this.jwhwbpy = jwhwbpy;
  }

}
