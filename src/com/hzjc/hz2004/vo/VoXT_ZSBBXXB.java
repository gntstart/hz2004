package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.*;
import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoXT_ZSBBXXB
    extends DefaultVO {

  /**
   *
   */
  public VoXT_ZSBBXXB() {
  }

  /**
   *
   * @param po
   */
  public VoXT_ZSBBXXB(PoXT_ZSBBXXB po) {
    //继承基类处理
    super(po);
    if (po.getBbsjmb() != null) {
      this.setBbsjmb(new String(encodeBytes(po.getBbsjmb())));
    }
  }

  /**
   *
   * @return
   */
  public PoXT_ZSBBXXB toPoXT_ZSBBXXB() {
    return toPoXT_ZSBBXXB(null);
  }

  /**
   *
   * @param po
   * @return
   */
  public PoXT_ZSBBXXB toPoXT_ZSBBXXB(PoXT_ZSBBXXB po) {
    if (po == null) {
      po = new PoXT_ZSBBXXB();
    }
    //对于非Blob字段进行处理
    po.setBbmc(this.getBbmc());
    po.setJlsj(this.getJlsj());
    po.setScrid(this.getScrid());
    po.setXgrid(this.getXgrid());
    po.setXgsj(this.getXgsj());
    po.setZsbbid(this.getZsbbid());
    po.setZsbbmbid(this.getZsbbmbid());
    //
    po.setBbsjmb(this.getBbsjmb() == null ? null : this.getBbsjmb().getBytes());
    ////////////////////////////////////////////////////////////////////
    return po;
  }

  private Long zsbbid;
  private Long zsbbmbid;
  private String bbmc;
  private String bbsjmb;
  private String jlsj;
  private Long scrid;
  private String xgsj;
  private Long xgrid;
  public String getBbmc() {
    return bbmc;
  }

  public String getBbsjmb() {
    return bbsjmb;
  }

  public String getJlsj() {
    return jlsj;
  }

  public Long getScrid() {
    return scrid;
  }

  public Long getXgrid() {
    return xgrid;
  }

  public String getXgsj() {
    return xgsj;
  }

  public Long getZsbbid() {
    return zsbbid;
  }

  public Long getZsbbmbid() {
    return zsbbmbid;
  }

  public void setZsbbmbid(Long zsbbmbid) {
    this.zsbbmbid = zsbbmbid;
  }

  public void setZsbbid(Long zsbbid) {
    this.zsbbid = zsbbid;
  }

  public void setXgsj(String xgsj) {
    this.xgsj = xgsj;
  }

  public void setXgrid(Long xgrid) {
    this.xgrid = xgrid;
  }

  public void setScrid(Long scrid) {
    this.scrid = scrid;
  }

  public void setJlsj(String jlsj) {
    this.jlsj = jlsj;
  }

  public void setBbsjmb(String bbsjmb) {
    this.bbsjmb = bbsjmb;
  }

  public void setBbmc(String bbmc) {
    this.bbmc = bbmc;
  }

}