package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.*;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoXT_ZSBBMBXXB
    extends DefaultVO {

  /**
   *
   */
  public VoXT_ZSBBMBXXB() {
  }

  /**
   *
   */
  public VoXT_ZSBBMBXXB(PoXT_ZSBBMBXXB po) {
    //继承基类处理
    super(po);
    if (po.getBbmb() != null) {
      this.setBbmb(new String(encodeBytes(po.getBbmb())));
    }
  }

  /**
   *
   * @return
   */
  public PoXT_ZSBBMBXXB toPoXT_ZSBBMBXXB() {
    return toPoXT_ZSBBMBXXB(null);
  }

  /**
   *
   * @param po
   * @return
   */
  public PoXT_ZSBBMBXXB toPoXT_ZSBBMBXXB(PoXT_ZSBBMBXXB po) {
    if (po == null) {
      po = new PoXT_ZSBBMBXXB();
    }
    po.setBbmbmc(this.getBbmbmc());
    po.setJlsj(this.getJlsj());
    po.setScrid(this.getScrid());
    po.setXgrid(this.getXgrid());
    po.setXgsj(this.getXgsj());
    po.setZsbblb(this.getZsbblb());
    po.setZsbbmbid(this.getZsbbmbid());
    //
    po.setBbmb(this.getBbmb() == null ? null : this.getBbmb().getBytes());
    ////////////////////////////////////////////////////////////////////
    return po;
  }

  private Long zsbbmbid;
  private String zsbblb;
  private String bbmbmc;
  private String bbmb;
  private String jlsj;
  private Long scrid;
  private String xgsj;
  private Long xgrid;
  public String getBbmb() {
    return bbmb;
  }

  public String getBbmbmc() {
    return bbmbmc;
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

  public String getZsbblb() {
    return zsbblb;
  }

  public Long getZsbbmbid() {
    return zsbbmbid;
  }

  public void setZsbbmbid(Long zsbbmbid) {
    this.zsbbmbid = zsbbmbid;
  }

  public void setZsbblb(String zsbblb) {
    this.zsbblb = zsbblb;
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

  public void setBbmbmc(String bbmbmc) {
    this.bbmbmc = bbmbmc;
  }

  public void setBbmb(String bbmb) {
    this.bbmb = bbmb;
  }

}