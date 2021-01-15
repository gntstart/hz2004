package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoHJXX_HGLGXB;
import com.hzjc.hz2004.po.PoHJXX_HXXB;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

public class VoHgxHqFhxx
    extends DefaultVO {

  //户关联关系信息
  private Long glid; //关联ID
  private Long hhid; //户号ID
  private Long glhhid; //关联户号ID
  private String glgx; //关联关系
  private String jljlsj; //记录建立时间
  private Long jljlrid; //记录建立人ID
  private String zt; //状态

  //被关联的户信息
  private Long hhnbid; //户号内部ID
  private Long mlpnbid; //门楼牌内部ID
  private String hh; //户号
  private String hlx; //户类型
  private String jhlb; //建户类别
  private String chlb; //撤户类别
  private String jhsj; //建户时间
  private String chsj; //撤户时间
  private Long cjhjywid; //创建户籍业务ID
  private Long cchjywid; //撤除户籍业务ID
  private String bdfw; //变动范围
  private String bdyy; //变动原因
  private String hhzt; //户号状态
  private Long lxdbid; //离线DBID
  private String jlbz; //记录标志
  private String qysj; //户用时间
  private String jssj; //结束时间
  private String cxbz; //冲销标志

  public VoHgxHqFhxx() {
  }

  public VoHgxHqFhxx(PoHJXX_HGLGXB poHglgx, PoHJXX_HXXB poHxx) {
    try {
      BeanUtils.copyProperties(this, poHglgx);
      BeanUtils.copyProperties(this, poHxx);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  public String getBdfw() {
    return bdfw;
  }

  public void setBdfw(String bdfw) {
    this.bdfw = bdfw;
  }

  public String getBdyy() {
    return bdyy;
  }

  public void setBdyy(String bdyy) {
    this.bdyy = bdyy;
  }

  public Long getCchjywid() {
    return cchjywid;
  }

  public void setCchjywid(Long cchjywid) {
    this.cchjywid = cchjywid;
  }

  public String getChlb() {
    return chlb;
  }

  public void setChlb(String chlb) {
    this.chlb = chlb;
  }

  public String getChsj() {
    return chsj;
  }

  public void setChsj(String chsj) {
    this.chsj = chsj;
  }

  public Long getCjhjywid() {
    return cjhjywid;
  }

  public void setCjhjywid(Long cjhjywid) {
    this.cjhjywid = cjhjywid;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public String getGlgx() {
    return glgx;
  }

  public void setGlgx(String glgx) {
    this.glgx = glgx;
  }

  public Long getGlhhid() {
    return glhhid;
  }

  public void setGlhhid(Long glhhid) {
    this.glhhid = glhhid;
  }

  public Long getGlid() {
    return glid;
  }

  public void setGlid(Long glid) {
    this.glid = glid;
  }

  public String getHh() {
    return hh;
  }

  public void setHh(String hh) {
    this.hh = hh;
  }

  public Long getHhid() {
    return hhid;
  }

  public void setHhid(Long hhid) {
    this.hhid = hhid;
  }

  public Long getHhnbid() {
    return hhnbid;
  }

  public void setHhnbid(Long hhnbid) {
    this.hhnbid = hhnbid;
  }

  public String getHhzt() {
    return hhzt;
  }

  public void setHhzt(String hhzt) {
    this.hhzt = hhzt;
  }

  public String getHlx() {
    return hlx;
  }

  public void setHlx(String hlx) {
    this.hlx = hlx;
  }

  public String getJhlb() {
    return jhlb;
  }

  public void setJhlb(String jhlb) {
    this.jhlb = jhlb;
  }

  public String getJhsj() {
    return jhsj;
  }

  public void setJhsj(String jhsj) {
    this.jhsj = jhsj;
  }

  public String getJlbz() {
    return jlbz;
  }

  public void setJlbz(String jlbz) {
    this.jlbz = jlbz;
  }

  public Long getJljlrid() {
    return jljlrid;
  }

  public void setJljlrid(Long jljlrid) {
    this.jljlrid = jljlrid;
  }

  public String getJljlsj() {
    return jljlsj;
  }

  public void setJljlsj(String jljlsj) {
    this.jljlsj = jljlsj;
  }

  public String getJssj() {
    return jssj;
  }

  public void setJssj(String jssj) {
    this.jssj = jssj;
  }

  public Long getLxdbid() {
    return lxdbid;
  }

  public void setLxdbid(Long lxdbid) {
    this.lxdbid = lxdbid;
  }

  public Long getMlpnbid() {
    return mlpnbid;
  }

  public void setMlpnbid(Long mlpnbid) {
    this.mlpnbid = mlpnbid;
  }

  public String getQysj() {
    return qysj;
  }

  public void setQysj(String qysj) {
    this.qysj = qysj;
  }

  public String getZt() {
    return zt;
  }

  public void setZt(String zt) {
    this.zt = zt;
  }

}