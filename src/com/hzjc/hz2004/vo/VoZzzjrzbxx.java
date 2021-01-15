package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoZZZJ_ZZZJRZB;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class VoZzzjrzbxx extends DefaultVO {
  public VoZzzjrzbxx() {
  }

  public VoZzzjrzbxx(PoZZZJ_ZZZJRZB poZZZJ_ZZZJRZB) {
    try {
      BeanUtils.copyProperties(this, poZZZJ_ZZZJRZB);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  private Long zzzjid;
  private String xm;
  private String gmsfhm;
  private String xb;
  private String mz;
  private String csrq;
  private String sldw;
  private Long yhid;
  private String czsj;
  private String czip;
  private String hh;
  private String ssxq;
  private String jlx;
  private String pcs;
  private String zrq;
  private String xzjd;
  private String jcwh;
  private Long rynbid;
  private Long ryid;
  private String czysfhm;
  private String czjg;
  private String czyktbh;
  private String dzjdz;
  private String sfzzjwz;
  private String zjsbbh;

  public void setZzzjid(Long zzzjid) {
    this.zzzjid = zzzjid;
  }

  public Long getZzzjid() {
    return zzzjid;
  }

  public void setXm(String xm) {
    this.xm = xm;
  }

  public String getXm() {
    return xm;
  }

  public void setGmsfhm(String gmsfhm) {
    this.gmsfhm = gmsfhm;
  }

  public String getGmsfhm() {
    return gmsfhm;
  }

  public void setXb(String xb) {
    this.xb = xb;
  }

  public String getXb() {
    return xb;
  }

  public void setMz(String mz) {
    this.mz = mz;
  }

  public String getMz() {
    return mz;
  }

  public void setCsrq(String csrq) {
    this.csrq = csrq;
  }

  public String getCsrq() {
    return csrq;
  }

  public void setSldw(String sldw) {
    this.sldw = sldw;
  }

  public String getSldw() {
    return sldw;
  }

  public void setYhid(Long yhid) {
    this.yhid = yhid;
  }

  public Long getYhid() {
    return yhid;
  }

  public void setCzsj(String czsj) {
    this.czsj = czsj;
  }

  public String getCzsj() {
    return czsj;
  }

  public void setCzip(String czip) {
    this.czip = czip;
  }

  public String getCzip() {
    return czip;
  }

  public String getHh() {
    return hh;
  }

  public void setHh(String hh) {
    this.hh = hh;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public String getJlx() {
    return jlx;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getPcs() {
    return pcs;
  }

  public void setPcs(String pcs) {
    this.pcs = pcs;
  }

  public String getZrq() {
    return zrq;
  }

  public void setZrq(String zrq) {
    this.zrq = zrq;
  }

  public String getXzjd() {
    return xzjd;
  }

  public void setXzjd(String xzjd) {
    this.xzjd = xzjd;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setCzysfhm(String czysfhm) {
    this.czysfhm = czysfhm;
  }

  public String getCzysfhm() {
    return czysfhm;
  }

  public void setCzjg(String czjg) {
    this.czjg = czjg;
  }

  public String getCzjg() {
    return czjg;
  }

  public void setCzyktbh(String czyktbh) {
    this.czyktbh = czyktbh;
  }

  public String getCzyktbh() {
    return czyktbh;
  }

  public void setDzjdz(String dzjdz) {
    this.dzjdz = dzjdz;
  }

  public String getDzjdz() {
    return dzjdz;
  }

  public void setSfzzjwz(String sfzzjwz) {
    this.sfzzjwz = sfzzjwz;
  }

  public String getSfzzjwz() {
    return sfzzjwz;
  }

  public void setZjsbbh(String zjsbbh) {
    this.zjsbbh = zjsbbh;
  }

  public String getZjsbbh() {
    return zjsbbh;
  }

}
