package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoZJYW_ZZHKXXB;

/**
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoZJYW_ZZHKXXB
    extends DefaultVO {
  public VoZJYW_ZZHKXXB() {
  }

  public VoZJYW_ZZHKXXB(PoZJYW_ZZHKXXB po) {
    //继承基类处理
    super(po);
    //////////////////////////////////////////////////////////////////
    //byte[]-->String(对于byte[]字节流的字段要特殊处理:Base64编码后赋给VO)
    ///////////////////////////////////////////////////////////////////
    if (po.getJdzp() != null) {
      this.setJdzp(new String(encodeBytes(po.getJdzp())));
    }

  }

  /**
   *
   * @return
   */
  public PoZJYW_ZZHKXXB toPoZJYW_ZZHKXXB() {
    return toPoZJYW_ZZHKXXB(null);
  }

  /**
   *
   * @param po
   * @return
   */
  public PoZJYW_ZZHKXXB toPoZJYW_ZZHKXXB(PoZJYW_ZZHKXXB po) {
    if (po == null) {
      po = new PoZJYW_ZZHKXXB();
    }
    po.setCxbz(this.getCxbz());
    po.setKtglh(this.getKtglh());
    po.setNbslid(this.getNbslid());
    po.setRynbid(this.getRynbid());
    po.setZjywid(this.getZjywid());
    po.setZzdwffrq(this.getZzdwffrq());
    po.setZzhkid(this.getZzhkid());
    po.setJdzp(decodeBytes(this.getJdzp() == null ? null :
                           this.getJdzp().getBytes()));
    ////////////////////////////////////////////////////////////////////
    return po;
  }

  private String cxbz;
  private Long zzhkid;
  private Long rynbid;
  private Long nbslid;
  private String ktglh;
  private String zzdwffrq;
  private Long zjywid;
  private String jdzp;
  public String getCxbz() {
    return cxbz;
  }

  public String getJdzp() {
    return jdzp;
  }

  public String getKtglh() {
    return ktglh;
  }

  public Long getNbslid() {
    return nbslid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public String getZzdwffrq() {
    return zzdwffrq;
  }

  public Long getZzhkid() {
    return zzhkid;
  }

  public void setZzhkid(Long zzhkid) {
    this.zzhkid = zzhkid;
  }

  public void setZzdwffrq(String zzdwffrq) {
    this.zzdwffrq = zzdwffrq;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public void setKtglh(String ktglh) {
    this.ktglh = ktglh;
  }

  public void setJdzp(String jdzp) {
    this.jdzp = jdzp;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

}