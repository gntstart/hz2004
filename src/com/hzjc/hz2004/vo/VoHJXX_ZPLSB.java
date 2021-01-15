package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoHJXX_ZPLSB;

public class VoHJXX_ZPLSB
    extends DefaultVO {

  public VoHJXX_ZPLSB() {

  }

  public VoHJXX_ZPLSB(PoHJXX_ZPLSB po) {
    //继承基类处理
    super(po);
    //////////////////////////////////////////////////////////////////
    //byte[]-->String(对于byte[]字节流的字段要特殊处理:Base64编码后赋给VO)
    ///////////////////////////////////////////////////////////////////
    if (po.getZp() != null) {
      this.setZp(new String(encodeBytes(po.getZp())));
    }
  }

  public PoHJXX_ZPLSB toPoHJXX_ZPLSB(PoHJXX_ZPLSB po) {
    if (po == null) {
      po = new PoHJXX_ZPLSB();
    }
    po.setZplsid(this.getZplsid());
    po.setSlh(this.getSlh());
    po.setGmsfhm(this.getGmsfhm());
    po.setZpid(this.getZpid());
    po.setRyid(this.getRyid());
    po.setRynbid(this.getRynbid());
    po.setBcsj(this.getBcsj());
    po.setCzrid(this.getCzrid());
    po.setJlbz(this.getJlbz());
    po.setZp(decodeBytes(this.getZp() == null ? null :
                         this.getZp().getBytes()));

    return po;
  }

  public PoHJXX_ZPLSB toPoHJXX_ZPLSB() {
    return this.toPoHJXX_ZPLSB(null);
  }

  private Long zplsid;
  private String slh;
  private String gmsfhm;
  private Long zpid;
  private Long ryid;
  private Long rynbid;
  private String zp; //经过BASE64进行编码的字符串数据
  private String bcsj;
  private Long czrid;
  private String jlbz;

  public String getBcsj() {
    return bcsj;
  }

  public void setBcsj(String bcsj) {
    this.bcsj = bcsj;
  }

  public Long getCzrid() {
    return czrid;
  }

  public void setCzrid(Long czrid) {
    this.czrid = czrid;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public String getSlh() {
    return slh;
  }

  public void setSlh(String slh) {
    this.slh = slh;
  }

  public String getZp() {
    return zp;
  }

  public void setZp(String zp) {
    this.zp = zp;
  }

  public Long getZpid() {
    return zpid;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public Long getZplsid() {
    return zplsid;
  }

  public void setZplsid(Long zplsid) {
    this.zplsid = zplsid;
  }

  public void setJlbz(String jlbz) {
    this.jlbz = jlbz;
  }

  public String getJlbz() {
    return jlbz;
  }
}
