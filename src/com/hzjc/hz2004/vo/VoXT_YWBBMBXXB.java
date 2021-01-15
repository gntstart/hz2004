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

public class VoXT_YWBBMBXXB
    extends DefaultVO {

  /**
   *
   */
  public VoXT_YWBBMBXXB() {
  }

  /**
   *
   * @param po
   */
  public VoXT_YWBBMBXXB(PoXT_YWBBMBXXB po) {
    //继承基类处理
    super(po);
    //////////////////////////////////////////////////////////////////
    //byte[]-->String(对于Byte[]字节字段特殊处理:编码后赋给VO)
    ///////////////////////////////////////////////////////////////////
    if (po.getBbmb() != null) {
      this.setBbmb(new String(encodeBytes(po.getBbmb())));
    }
  }

  /**
   *
   * @return
   */
  public PoXT_YWBBMBXXB toPoXT_YWBBMBXXB() {
    return toPoXT_YWBBMBXXB(null);
  }

  /**
   *
   * @param po
   * @return
   */
  public PoXT_YWBBMBXXB toPoXT_YWBBMBXXB(PoXT_YWBBMBXXB po) {
    if (po == null) {
      po = new PoXT_YWBBMBXXB();
    }
    //对于非Blob字段进行处理
    po.setBbmc(this.getBbmc());
    po.setJlrid(this.getJlrid());
    po.setJlsj(this.getJlsj());
    po.setXgrid(this.getXgrid());
    po.setXgsj(this.getXgsj());
    po.setYwbbid(this.getYwbbid());
    po.setYwbblb(this.getYwbblb());
    //Long Raw 字段处理
    po.setBbmb(this.getBbmb() == null ? null : this.getBbmb().getBytes());
    return po;
  }

  private Long ywbbid;
  private String ywbblb;
  private String bbmc;
  private String bbmb;
  private String jlsj;
  private Long jlrid;
  private String xgsj;
  private Long xgrid;
  public String getBbmb() {
    return bbmb;
  }

  public String getBbmc() {
    return bbmc;
  }

  public Long getJlrid() {
    return jlrid;
  }

  public String getJlsj() {
    return jlsj;
  }

  public Long getXgrid() {
    return xgrid;
  }

  public String getXgsj() {
    return xgsj;
  }

  public Long getYwbbid() {
    return ywbbid;
  }

  public String getYwbblb() {
    return ywbblb;
  }

  public void setYwbblb(String ywbblb) {
    this.ywbblb = ywbblb;
  }

  public void setYwbbid(Long ywbbid) {
    this.ywbbid = ywbbid;
  }

  public void setXgsj(String xgsj) {
    this.xgsj = xgsj;
  }

  public void setXgrid(Long xgrid) {
    this.xgrid = xgrid;
  }

  public void setJlsj(String jlsj) {
    this.jlsj = jlsj;
  }

  public void setJlrid(Long jlrid) {
    this.jlrid = jlrid;
  }

  public void setBbmc(String bbmc) {
    this.bbmc = bbmc;
  }

  public void setBbmb(String bbmb) {
    this.bbmb = bbmb;
  }

}