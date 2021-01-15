package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoXT_BSSQB;
import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import com.hzjc.wsstruts.exception.*;
import java.lang.reflect.*;

/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 本市市区操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */

public class VoXtbssq
    extends DefaultVO {

  //本市市区
  private String sqbz;
  private String gxbz;
  private Long sqid;
  private String qhdm;
  private String qybz;
  private String bdlx;
  private String bdsj;

  //行政区划
  private String dm;
  private String mc;
  private String zwpy;
  private String wbpy;
  private String bz;
  private String xzqhqybz;
  private String xzqhbdlx;
  private String xzqhbdsj;
  private String sqbzmc;
  private String gxbzmc;
  private String qybzmc;

  public VoXtbssq() {
  }

  public VoXtbssq(PoXT_BSSQB poXtbssqb, PoXT_XZQHB poXtxzqhb,
                  PoXT_XTCSB poXtcsba, PoXT_XTCSB poXtcsbb,
                  PoXT_XTCSB poXtcsbc) {
    try {
      BeanUtils.copyProperties(this, poXtxzqhb);
      BeanUtils.copyProperties(this, poXtbssqb);
      this.setSqbzmc(poXtcsba.getMc());
      this.setGxbzmc(poXtcsbb.getMc());
      this.setQybzmc(poXtcsbc.getMc());
      this.setXzqhbdlx(poXtxzqhb.getBdlx());
      this.setXzqhbdsj(poXtxzqhb.getBdsj());
      this.setXzqhqybz(poXtxzqhb.getBz());
    }
    catch (InvocationTargetException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
  }

  //本市市区
  public void setSqbz(String sqbz) {
    this.sqbz = sqbz;
  }

  public String getSqbz() {
    return sqbz;
  }

  public void setGxbz(String gxbz) {
    this.gxbz = gxbz;
  }

  public String getGxbz() {
    return gxbz;
  }

  public void setSqid(Long sqid) {
    this.sqid = sqid;
  }

  public Long getSqid() {
    return sqid;
  }

  public void setQhdm(String qhdm) {
    this.qhdm = qhdm;
  }

  public String getQhdm() {
    return qhdm;
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

//行政区划
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

  public void setXzqhqybz(String qybz) {
    this.xzqhqybz = qybz;
  }

  public String getXzqhqybz() {
    return xzqhqybz;
  }

  public void setXzqhbdlx(String bdlx) {
    this.xzqhbdlx = bdlx;
  }

  public String getXzqhbdlx() {
    return xzqhbdlx;
  }

  public void setXzqhbdsj(String bdsj) {
    this.xzqhbdsj = bdsj;
  }

  public String getXzqhbdsj() {
    return xzqhbdsj;
  }

  public String getSqbzmc() {
    return sqbzmc;
  }

  public void setSqbzmc(String sqbzmc) {
    this.sqbzmc = sqbzmc;
  }

  public String getGxbzmc() {
    return gxbzmc;
  }

  public void setGxbzmc(String gxbzmc) {
    this.gxbzmc = gxbzmc;
  }

  public String getQybzmc() {
    return qybzmc;
  }

  public void setQybzmc(String qybzmc) {
    this.qybzmc = qybzmc;
  }

}
