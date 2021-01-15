package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;
import com.hzjc.hz2004.po.PoZJYW_SLXXB;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 *
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: GNT Corp.</p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoZJYW_SLXXB
    extends DefaultVO {

  private Long nbslid;
  private String slh;
  private Long ryid;
  private Long rynbid;
  private Long zpid;
  private String qfjg;
  private String yxqxqsrq;
  private String yxqxjzrq;
  private String zz;
  private String slyy;
  private String zzlx;
  private String lqfs;
  private String sflx;
  private String sjblsh;
  private String slzt;
  private Long zjywid;
  private String cxbz;
  private String cxsj;
  private Long cxrid;
  private Long cxzjywid;
  private Float sfje;

  public VoZJYW_SLXXB() {
  }

  public VoZJYW_SLXXB(PoZJYW_SLXXB poSlxx) {
    try {
      BeanUtils.copyProperties(this, poSlxx);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  public void setNbslid(Long nbslid) {
    this.nbslid = nbslid;
  }

  public Long getNbslid() {
    return nbslid;
  }

  public void setSlh(String slh) {
    this.slh = slh;
  }

  public String getSlh() {
    return slh;
  }

  public void setRyid(Long ryid) {
    this.ryid = ryid;
  }

  public Long getRyid() {
    return ryid;
  }

  public void setRynbid(Long rynbid) {
    this.rynbid = rynbid;
  }

  public Long getRynbid() {
    return rynbid;
  }

  public void setZpid(Long zpid) {
    this.zpid = zpid;
  }

  public Long getZpid() {
    return zpid;
  }

  public void setQfjg(String qfjg) {
    this.qfjg = qfjg;
  }

  public String getQfjg() {
    return qfjg;
  }

  public void setYxqxqsrq(String yxqxqsrq) {
    this.yxqxqsrq = yxqxqsrq;
  }

  public String getYxqxqsrq() {
    return yxqxqsrq;
  }

  public void setYxqxjzrq(String yxqxjzrq) {
    this.yxqxjzrq = yxqxjzrq;
  }

  public String getYxqxjzrq() {
    return yxqxjzrq;
  }

  public void setZz(String zz) {
    this.zz = zz;
  }

  public String getZz() {
    return zz;
  }

  public void setSlyy(String slyy) {
    this.slyy = slyy;
  }

  public String getSlyy() {
    return slyy;
  }

  public void setZzlx(String zzlx) {
    this.zzlx = zzlx;
  }

  public String getZzlx() {
    return zzlx;
  }

  public void setLqfs(String lqfs) {
    this.lqfs = lqfs;
  }

  public String getLqfs() {
    return lqfs;
  }

  public void setSflx(String sflx) {
    this.sflx = sflx;
  }

  public String getSflx() {
    return sflx;
  }

  public void setSfje(Float sfje) {
    this.sfje = sfje;
  }

  public Float getSfje() {
    return sfje;
  }

  public void setSjblsh(String sjblsh) {
    this.sjblsh = sjblsh;
  }

  public String getSjblsh() {
    return sjblsh;
  }

  public void setSlzt(String slzt) {
    this.slzt = slzt;
  }

  public String getSlzt() {
    return slzt;
  }

  public void setZjywid(Long zjywid) {
    this.zjywid = zjywid;
  }

  public Long getZjywid() {
    return zjywid;
  }

  public void setCxbz(String cxbz) {
    this.cxbz = cxbz;
  }

  public String getCxbz() {
    return cxbz;
  }

  public void setCxsj(String cxsj) {
    this.cxsj = cxsj;
  }

  public String getCxsj() {
    return cxsj;
  }

  public void setCxrid(Long cxrid) {
    this.cxrid = cxrid;
  }

  public Long getCxrid() {
    return cxrid;
  }

  public void setCxzjywid(Long cxzjywid) {
    this.cxzjywid = cxzjywid;
  }

  public Long getCxzjywid() {
    return cxzjywid;
  }

}